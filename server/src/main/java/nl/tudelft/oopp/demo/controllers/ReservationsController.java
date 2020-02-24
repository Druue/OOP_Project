package nl.tudelft.oopp.demo.controllers;


import nl.tudelft.oopp.demo.database_queries.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/reservations")
public class ReservationsController {

    final
    ReservationService service;

    public ReservationsController(ReservationService service) {
        this.service = service;
    }


    /* This method calls the ReservationService getReservations( NetID ) method
     with the provided by the cookie value NetID to find all the current reservations
     of a particular user. The result is then sent as a list of reservation objects with
     information about every one of them
    */
    @RequestMapping("/")
    @ResponseBody
    public void requestUserReservations(@CookieValue("NetID") String NetID) {

    }

}
