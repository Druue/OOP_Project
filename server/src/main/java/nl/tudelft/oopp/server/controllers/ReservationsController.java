package nl.tudelft.oopp.server.controllers;

import java.util.List;
import javassist.NotFoundException;
import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.server.models.ReservableTimeslotException;
import nl.tudelft.oopp.server.models.Reservation;
import nl.tudelft.oopp.server.models.TimeSlot;
import nl.tudelft.oopp.server.models.User;
import nl.tudelft.oopp.server.models.UserReservationsIntersectionException;
import nl.tudelft.oopp.server.services.LoggerService;
import nl.tudelft.oopp.server.services.ReservationService;
import nl.tudelft.oopp.server.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    // Connection with the methods for querying the database for the reservations
    private ReservationService reservationService;
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(ReservationsController.class);

    public ReservationsController(ReservationService reservationService, UserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
    }


    /**
     * Sends all reservations in the database to the requesting administrator.
     * @return A {@link ResponseEntity} object containing all the current
     *      reservations in the database.
     */
    @GetMapping("/admin/all")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        logger.info("Received GET request for all reservations");

        List<Reservation> responseList = reservationService.getAllReservations();
        return ResponseEntity.ok(responseList);
    }

    /** Receives a GET request for all current reservations and sends them in the response.
     * @return A {@link ResponseEntity} object containing the list of current reservations.
     */
    @GetMapping("/admin/current")
    public ResponseEntity<List<Reservation>> getCurrentReservations() {
        logger.info("Received GET request for all current reservations. Processing ...");
        List<Reservation> responseList =  reservationService.getAllCurrentReservations();
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
    @GetMapping("/user/all")
    public ResponseEntity<List<Reservation>> getUserReservations(ClientRequest<String> request) {
        logger.info("Received GET request for user reservations. Processing ...");

        String username = request.getUsername();
        User foundUser = null;

        try {
            foundUser = userService.getUserByUsername(username);
        } catch (NotFoundException e) {
            logger.error("User with username: " + username + " not found.");
            return ResponseEntity.badRequest().build();
        }

        logger.info("User with username: " + username + " successfully found.");

        Long userId = foundUser.id;
        List<Reservation> foundReservations = reservationService.getReservationsByUserID(userId);
        return ResponseEntity.ok(foundReservations);
    }

    /**
     * Receive a GET request for the current reservations of a user.
     *
     * @param request The request containing the username of the user to get the reservations of.
     * @return A {@link ResponseEntity} object containing a list of {@link Reservation} objects
     *      that represent the current reservations of the user.
     */
    @GetMapping("/user/current")
    public ResponseEntity<List<Reservation>> getCurrentUserReservations(
        ClientRequest<String> request) {
        logger.info("Received GET request for current user reservations. Processing ...");

        String username = request.getUsername();
        User foundUser = null;

        try {
            foundUser = userService.getUserByUsername(username);
        } catch (NotFoundException e) {
            logger.error("User with username: " + username + " not found.");
            return ResponseEntity.badRequest().build();
        }

        logger.info("User with username: " + username + " successfully found.");

        Long userId = foundUser.id;
        List<Reservation> foundReservations =
            reservationService.getCurrentUserReservations(userId);

        return ResponseEntity.ok(foundReservations);
    }

    /**
     * Adds a reservation to the database.
     *
     * @param request The {@link ClientRequest} object containing the {@link Reservation} info.
     *                that is used to save the new reservation.
     * @return ResponseEntity object indicating whether the reservation was added successfully.
     */
    @PostMapping("/{role:(?:user|admin)}/add")
    public ResponseEntity<String> addReservation(ClientRequest<Reservation> request) {
        logger.info("Received POST request for a new reservation from user: "
                + request.getUsername() + ". Processing ...");

        Reservation newReservation = request.getBody();
        newReservation.timeslot = new TimeSlot(newReservation.timeslot.startTime,
                                                newReservation.timeslot.endTime);
        try {
            reservationService.addReservation(newReservation);

        } catch (UserReservationsIntersectionException e) {
            return ResponseEntity.badRequest().body("Failure to create reservations. "
                + "Intersection found with other current user reservations.");
        } catch (ReservableTimeslotException e) {
            return ResponseEntity.badRequest().body("Failure to create reservations. "
                + "Intersection found current reservations of the reservable.");
        }

        logger.info("New reservation added successfully");

        return ResponseEntity.ok().build();
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
    public ResponseEntity<String> deleteReservation(@RequestParam Long id) {

        logger.info("Received DELETE request for reservation. Processing ...");

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
