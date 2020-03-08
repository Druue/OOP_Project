package nl.tudelft.oopp.server.controllers;

import java.util.List;

import nl.tudelft.oopp.server.models.Reservable;
import nl.tudelft.oopp.server.services.ReservableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class ReservableController {

    /**
     * Importing the methods from the service class.
     */
    @Autowired
    ReservableService service;


    /**
     * @return all of the reservables.
     */
    @RequestMapping("/models/Reservable")
    public List<Reservable> getAllReservables() {
        return service.getAllReservables();
    }

    /**
     * @param id
     * @return a reservable.
     */
    @RequestMapping("models/Reservable/{id}")
    public Reservable getReservable(@PathVariable Long id) {
        return service.getReservable(id).get();
    }


    @PostMapping("models/Reservable")
    public void create(@RequestBody Reservable newReservable) {
        service.addReservable(newReservable);
    }

    /**
     * @param id
     * @return updates an existing reservable inside of the database.
     */
    @PutMapping("models/Reservable/{id}")
    public void update(@RequestBody Reservable newReservable, @PathVariable Long id) {
        service.updateReservable(id, newReservable);
    }

    /**
     * @param id
     * @return the deleted reservable accoridng to the reservable id.
     */
    @DeleteMapping("/Reservable/{id}")
    public void delete(@PathVariable Long id) {

        service.deleteReservable(id);
    }


}
