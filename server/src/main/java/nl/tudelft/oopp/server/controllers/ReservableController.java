package nl.tudelft.oopp.server.controllers;

import java.util.List;
import nl.tudelft.oopp.server.models.Reservable;
import nl.tudelft.oopp.server.services.ReservableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ReservableController {

    /**
     * Importing the methods from the service class.
     */
    @Autowired
    ReservableService service;


    /**
     * Gets a list of all reservables stored in the database.
     *
     * @return all of the reservables.
     */
    @RequestMapping("/models/Reservable")
    public List<Reservable> getAllReservables() {
        return service.getAllReservables();
    }

    /**
     * Gets a specific reservable from the database.
     *
     * @param id the id to look for.
     * @return the corresponding reservable.
     */
    @RequestMapping("models/Reservable/{id}")
    public Reservable getReservable(@PathVariable Long id) {
        return service.getReservable(id).get();
    }


    /**
     * Add a new Reservable to the database.
     *
     * @param newReservable the reservable to add.
     */
    @PostMapping("models/Reservable")
    public void create(@RequestBody Reservable newReservable) {
        service.addReservable(newReservable);
    }

    /**
     * Updates an existing reservable inside of the database.
     *
     * @param id The ID of the reservable to update.
     */
    @PutMapping("models/Reservable/{id}")
    public void update(@RequestBody Reservable newReservable, @PathVariable Long id) {
        service.updateReservable(id, newReservable);
    }

    /**
     * Deletes a specific reservable from the database.
     *
     * @param id the ID of the reservable to delete.
     */
    @DeleteMapping("/Reservable/{id}")
    public void delete(@PathVariable Long id) {

        service.deleteReservable(id);
    }


}
