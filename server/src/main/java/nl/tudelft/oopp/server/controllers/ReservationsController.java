package nl.tudelft.oopp.server.controllers;

import javax.servlet.http.HttpSession;
import nl.tudelft.oopp.server.services.LoggerService;
import nl.tudelft.oopp.server.services.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    // Connection with the methods for querying the database for the reservations
    private ReservationService service;

    public ReservationsController(ReservationService service) {
        this.service = service;
    }


    /**
     * This method calls the ReservationService getReservations( NetID ) method
     * with the provided by the cookie value NetID to find all the current reservations
     * of a particular user. The result is then sent as a list of reservation objects with
     * information about every one of them.
     */
    @GetMapping("/")
    public ResponseEntity requestUserReservations(HttpSession session) {

        // Get the user's session and get the NetID attribute stored in it
        // to query the database for the user's reservations.
        // If the user does not have an existing session, the method logs an error message
        // and sends

        try {
            if (session.isNew()) {
                throw new IllegalAccessException();
            }

            String userNetID = (String) session.getAttribute("NetID");
            LoggerService.info(ReservationsController.class,"Client with NetID: " + userNetID
                                                            + " requested his current reservations.");
            return ResponseEntity.ok().build();
        } catch (IllegalAccessException e) {
            session.invalidate();
            LoggerService.error(ReservationsController.class,"Unauthorized attempt to view reservations -"
                                                             + " no existing session found for the client.");
            return ResponseEntity.badRequest().build();
        }

    }

}
