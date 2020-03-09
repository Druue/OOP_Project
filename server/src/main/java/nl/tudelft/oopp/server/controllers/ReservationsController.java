package nl.tudelft.oopp.server.controllers;

import java.util.List;
import javassist.NotFoundException;
import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.api.models.ServerResponseAlert;
import nl.tudelft.oopp.server.models.Reservation;
import nl.tudelft.oopp.server.models.User;
import nl.tudelft.oopp.server.services.LoggerService;
import nl.tudelft.oopp.server.services.ReservationService;
import nl.tudelft.oopp.server.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    // Connection with the methods for querying the database for the reservations
    private ReservationService reservationService;
    private UserService userService;

    public ReservationsController(ReservationService reservationService, UserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
    }

    public static void logUserError(String username) {
        LoggerService.error(ReservationsController.class, "No user with username: "
            + username + " found");
    }

    /**
     * Endpoint for the procedure of getting user reservations.
     * Receives the username in the {@link ClientRequest} object and uses
     * to find the user in the database and then use his userID to find his reservations.
     *
     * @param request The request object containing the username to be used.
     * @return A List of Reservations object representing all the current reservations of the user.
     */
    @GetMapping("/find")
    public ResponseEntity<List<Reservation>> getUserReservations(ClientRequest<String> request) {
        LoggerService.info(ReservationsController.class,
            "Received request for user reservations. Processing ...");

        String username = request.getUsername();
        User foundUser = null;

        try {
            foundUser = userService.getUserByUsername(username);
        } catch (NotFoundException e) {
            logUserError(username);
            return ResponseEntity.badRequest().build();
        }

        LoggerService.info(ReservationsController.class, "User with username: "
            + username + " successfully found.");
        Long userID = foundUser.userId;

        List<Reservation> foundReservations = reservationService.getReservationsByUserID(userID);
        return new ResponseEntity<List<Reservation>>(foundReservations, HttpStatus.ACCEPTED);
    }

    /**
     * Adds a reservation to the database.
     *
     * @param request The {@link ClientRequest} object containing the {@link Reservation} info.
     *                that is used to save the new reservation.
     * @return ResponseEntity object indicating that
     */
    @PostMapping("/addNewOne")
    public ResponseEntity<ServerResponseAlert> addReservation(ClientRequest<Reservation> request) {
        LoggerService.info(ReservationsController.class,
            "Received POST request for a new reservation from user: "
                + request.getUsername() + ". Processing ...");
        String username = request.getUsername();

        reservationService.addReservation(request.getBody());

        LoggerService.info(ReservationsController.class,
            "New reservation added successfully");
        return ResponseEntity.ok(new ServerResponseAlert(
            "New reservation added successfully", "SUCCESS"));
    }

}
