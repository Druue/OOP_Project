package nl.tudelft.oopp.demo.controllers;
import nl.tudelft.oopp.demo.repositories.ReservableRepository;
import nl.tudelft.oopp.demo.models.Reservable;
import nl.tudelft.oopp.demo.services.ReservableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class ReservableController {

    @Autowired
    ReservableService service;

    /**
     * @return every single reservable in the database
     */
    @RequestMapping("/models/Reservable")
    public List<Reservable> getAllReservables() {
        return service.getAll();
    }

    /**
     * @param id
     * @return a single reservable from the database by id
     */
    @RequestMapping("models/Reservable/{id}")
    public Reservable getReservable(@PathVariable Long id ) {
        return service.getReservable(id).get();
    }

    /**
     * @param newReservable
     * @return the newly cerated reservable
     */
    @PostMapping("models/Reservable")
    public Reservable create(@RequestBody Reservable newReservable) {
        return service.addReservable(newReservable);
    }

    /**
     * @param id
     * @param body
     * @return updates an existing reservable inside of the database
     */
    @PutMapping("models/Reservable/{id}")
    public Reservable update(@RequestBody Reservable newReservable, @PathVariable Long id) {
        service.updateReservable(id, newReservable);
    }

    /**
     * @param id
     * @return the deleted reservable accoridng to the reservable id.
     */
    @DeleteMapping("/Reservable/{id}")
    public  void delete(@PathVariable Long id) {

        repository.deleteReservable(id);
    }



}
