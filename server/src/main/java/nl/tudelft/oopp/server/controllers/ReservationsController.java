package nl.tudelft.oopp.server.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.naming.AuthenticationException;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.api.models.ReservationResponse;
import nl.tudelft.oopp.api.models.ServerResponseAlert;
import nl.tudelft.oopp.server.models.AuthorizationException;
import nl.tudelft.oopp.server.models.Reservation;
import nl.tudelft.oopp.server.models.TimeSlot;
import nl.tudelft.oopp.server.models.TimeslotAlreadyReservedException;
import nl.tudelft.oopp.server.models.User;
import nl.tudelft.oopp.server.models.UserReservationsIntersectionException;
import nl.tudelft.oopp.server.services.AuthorizationService;
import nl.tudelft.oopp.server.services.LoggerService;
import nl.tudelft.oopp.server.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;





@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    public HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

    private ReservationService reservationService;
    private AuthorizationService authorizationService;
    private Logger logger = LoggerFactory.getLogger(ReservationsController.class);

    /** Used to create the {@link ReservationsController} bean with the provided information.
     * @param reservationService The ReservationService bean to use.
     * @param authorizationService The {@link AuthorizationService} bean to use
     */
    public ReservationsController(ReservationService reservationService,
                                  AuthorizationService authorizationService) {
        this.reservationService = reservationService;
        this.authorizationService = authorizationService;
    }

    /**
     * Sends all reservations in the database to the requesting administrator.
     *
     * @return A {@link ResponseEntity} object containing all the current
     *      reservations in the database.
     */
    @PostMapping("/admin/all")
    public ResponseEntity<List<Reservation>> getAllReservations(
        @RequestBody ClientRequest<String> request) {
        logger.info("Received POST request for all reservations");

        try {
            authorizationService.checkAuthorization(request.getUsername());
        } catch (AuthorizationException e) {
            logger.error(AuthorizationService.NOT_ADMIN);
            return ResponseEntity.badRequest().build();
        } catch (AuthenticationException e) {
            logger.error(AuthorizationService.NO_USER_FOUND);
            return ResponseEntity.badRequest().build();
        }

        List<Reservation> responseList = reservationService.getAllReservations();
        return ResponseEntity.ok(responseList);
    }


    /**
     * Receives a GET request for all current reservations and sends them in the response.
     *
     * @return A {@link ResponseEntity} object containing the list of current reservations.
     */
    @PostMapping("/admin/current")
    public ResponseEntity<List<Reservation>> getCurrentReservations() {

        logger.info("Received POST request for all current reservations. Processing ...");

        List<Reservation> responseList = reservationService.getAllCurrentReservations();

        logger.info("Sending all current reservations ...");
        return ResponseEntity.ok(responseList);
    }

    /**     Receives a POST request for all reservations for today. Then uses the
     *      {@link ReservationService} bean to fetch all reservations within the bounds
     *      of today and sends them encapsulated in a {@link ResponseEntity} object.
     *
     * @return  ResponseEntity object containing {@link List} of {@link Reservation} objects
     *          representing today's reservations.
     */
    @PostMapping("/admin/today")
    public ResponseEntity<List<Reservation>> getTodaysReservations() {

        logger.info("Received POST request for today's reservations. Processing ...");

        List<Reservation> responseList = reservationService.getAllReservationsForToday();

        logger.info("Sending all current reservations ...");
        return ResponseEntity.ok(responseList);
    }

    /**
     * Endpoint for the procedure of getting user reservations.
     * Receives the username in the {@link ClientRequest} object and uses
     * to find the user in the database and then use his userID to find his reservations.
     *
     * @param request The request object containing the username to be used.
     * @return A List of Reservations object representing all the current reservations of the user.
     */
    @PostMapping("/user/all")
    public ResponseEntity<List<Reservation>> getUserReservations(
        @RequestBody ClientRequest<String> request) {

        logger.info("Received POST request for user reservations. Processing ...");

        String username = request.getUsername();
        User foundUser;

        try {
            foundUser = authorizationService.authenticateUser(username);
        } catch (AuthenticationException e) {
            logger.error("User with username: " + username + " not found.");
            return ResponseEntity.badRequest().build();
        }

        logger.info("User with username: " + username + " successfully found.");

        Long userId = foundUser.id;
        List<Reservation> foundReservations = reservationService.getReservationsByUserID(userId);

        return ResponseEntity.ok(foundReservations);
    }

    /**
     * Receive a POST request for the current reservations of a user.
     *
     * @param request The request containing the username of the user to get the reservations of.
     * @return A {@link ResponseEntity} object containing a list of {@link Reservation} objects
     *      that represent the current reservations of the user.
     */
    @PostMapping("/user/current")
    public ResponseEntity<List<Reservation>> getCurrentUserReservations(
        @RequestBody ClientRequest<String> request) {
        logger.info("Received POST request for current user reservations. Processing ...");

        String username = request.getUsername();
        User foundUser;

        try {
            foundUser = authorizationService.authenticateUser(username);
        } catch (AuthenticationException e) {
            logger.error("User with username: " + username + " not found.");
            return ResponseEntity.badRequest().build();
        }

        logger.info("User with username: " + username + " successfully found.");

        Long userId = foundUser.id;
        List<Reservation> foundReservations =
            reservationService.getCurrentUserReservations(userId);

        return ResponseEntity.ok(foundReservations);
    }

    /**     This method resceives a POST request for all today's reservations of a particular user.
     *      It first authenticates the user using the authorizationService bean and then uses the id
     *      of the user to find his/her reservations for today using the {@link ReservationService}
     *      bean. Find all it sends back the fetched {@link List} of {@link Reservation} objects.
     * @param request   A {@link ClientRequest} object containing the username of the user whose
     *                  reservations should be fetched.
     * @return          A {@link ResponseEntity} object containing the list of the user's
     *                  reservations for today.
     */
    @PostMapping("/user/today")
    public ResponseEntity<List<Reservation>> getTodaysUserReservations(
        @RequestBody ClientRequest<String> request) {

        logger.info("Received POST request for today's reservations of user: "
            + request.getUsername() + ". Processing ...");

        String username = request.getUsername();
        User foundUser;

        try {
            foundUser = authorizationService.authenticateUser(username);
        } catch (AuthenticationException e) {
            logger.error("User with username: " + username + " not found.");
            return ResponseEntity.badRequest().build();
        }

        logger.info("User with username: " + username + " successfully found.");

        Long userId = foundUser.id;
        List<Reservation> responseList = reservationService.getUserReservationsForToday(userId);

        return ResponseEntity.ok(responseList);

    }

    /**
     * Adds a reservation to the database.
     *
     * @param request The {@link ClientRequest} object containing the {@link Reservation} info.
     *                that is used to save the new reservation.
     * @return ResponseEntity object indicating whether the reservation was added successfully.
     */
    @PostMapping("/{role:(?:user|admin)}/add")
    public ResponseEntity<ServerResponseAlert> addReservation(@RequestBody ClientRequest<Reservation> request) {
        logger.info("Received POST request for a new reservation from user: "
            + request.getUsername() + ". Processing ...");

        try {
            authorizationService.authenticateUser(request.getUsername());
        } catch (AuthenticationException e) {
            logger.error(AuthorizationService.NO_USER_FOUND);
            return ResponseEntity.badRequest().build();
        }

        Reservation newReservation = request.getBody();

        newReservation.timeslot = new TimeSlot(newReservation.timeslot.startTime,
            newReservation.timeslot.endTime);

        try {
            reservationService.addReservation(newReservation);
        } catch (UserReservationsIntersectionException e) {
            return ResponseEntity.badRequest().body(new ServerResponseAlert("Failure to create reservations. "
                + "Intersection found with other current user reservations.", "ERROR"));
        } catch (TimeslotAlreadyReservedException e) {
            return ResponseEntity.badRequest().body(new ServerResponseAlert("Failure to create reservations. "
                + "Intersection found with other current reservations of the reservable.", "ERROR"));
        }

        logger.info("New reservation added successfully");

        return ResponseEntity.ok(new ServerResponseAlert("New reservation added successfully", "CONFIRMATION"));
    }

    /**
     * Receives a DELETE request for a reservation with a provided id and removes it from
     * the database using the {@link ReservationService} deleteReservation method.
     * If the reservation is not found in the database, the method concludes it has
     * already been deleted and sends a bad request status as a response.
     *
     * @param id The request parameter containing the id of the reservation to be removed.
     * @return A {@link ResponseEntity} object indicating the success or failure of the delete
     *      operation depending on whether the reservation to be deleted was actually found
     *      in the database.
     */
    @DeleteMapping("/{role:(?:user|admin)}/delete")
    public ResponseEntity<String> deleteReservation(@RequestParam Long id,
                                                    @RequestBody ClientRequest<String> request) {

        logger.info("Received DELETE request for reservation. Processing ...");

        try {
            authorizationService.authenticateUser(request.getUsername());
        } catch (AuthenticationException e) {
            logger.error(AuthorizationService.NO_USER_FOUND);
            return ResponseEntity.badRequest().build();
        }

        try {
            reservationService.deleteReservation(id);
        } catch (Exception e) {
            LoggerService.error(ReservationsController.class, "Failure"
                + " to delete the reservation. It has already been deleted");
            return ResponseEntity.badRequest().build();
        }

        logger.info("Reservation successfully deleted");

        return ResponseEntity.ok().build();
    }
}
