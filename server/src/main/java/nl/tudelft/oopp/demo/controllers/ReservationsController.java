package nl.tudelft.oopp.demo.controllers;

import javax.servlet.http.HttpSession;
import nl.tudelft.oopp.demo.services.LoggerService;
import nl.tudelft.oopp.demo.services.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    // Connection with the methods for querying the database for the reservations
    // private ReservationService service;

    public ReservationsController(ReservationService service) {
        // this.service = service;
    }


    /**
     * This method calls the ReservationService getReservations( NetID ) method with the provided by
     * the cookie value NetID to find all the current reservations of a particular user. The result
     * is then sent as a list of reservation objects with information about every one of them.
     */
    @PutMapping("/")
    public <T> ResponseEntity<T> requestUserReservations(@RequestParam String netID,
                                                         @RequestParam String role) {

        // Get the user's session and get the NetID attribute stored in it
        // to query the database for the user's reservations.
        // If the user does not have an existing session, the method logs an error message
        // and sends

        LoggerService.info(ReservationsController.class,
            "Client with NetID: " + netID + " requested his current reservations.");
        return ResponseEntity.ok().build();
    }


    /**
     * This method checks if a session for the corresponding user
     * is already existing.
     *
     * @param session The session object to check
     * @throws IllegalAccessException Throws it if the session has not existed till now
     *                                which means the user must first log in.
     */
    private void sessionValidate(HttpSession session) throws IllegalAccessException {
        if (session.getAttribute("NetID") == null) {
            throw new IllegalAccessException();
        }
    }

}
