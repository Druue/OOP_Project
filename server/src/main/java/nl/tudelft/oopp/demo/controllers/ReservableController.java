package nl.tudelft.oopp.demo.controllers;
import nl.tudelft.oopp.demo.repositories.ReservableRepository;
import nl.tudelft.oopp.demo.models.Reservable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class ReservableController {

    @Autowired
    ReservableRepository repository;

    public ReservableRepository getRepository () {
        return repository;
    }

    public void setRepository(ReservableRepository repository) {
        this.repository = repository;
    }


    /**
     * @return the reservable
     */
    @GetMapping(value = "/Reservable")
    public List<Reservable> index() {
        return repository.findAll();
    }

    /**
     * @param newReservable
     * @return the newly cerated reservable
     */
    @PostMapping("/Reservable/search")
    public Reservable create(@RequestBody Reservable newReservable) {
        return repository.save(newReservable);
    }

    /**
     * @param id
     * @return creates reservable from the sent information to the server
     */
    @GetMapping("/Reservable/{id}")
    public Reservable findById(@PathVariable Long id ) {
        return repository.findById(id).get();
    }

    /**
     * @param id
     * @param body
     * @return updates an existing reservable inside of the database
     */
    @PutMapping("/Reservable/{id}")
    public Reservable update(@RequestBody Reservable newReservable, @PathVariable Long id) {

        return repository.findById(id).map(reservable -> {
            reservable.setName(newReservable.getName());
            reservable.setIsAvailable(newReservable.getIsAvailable());
            return repository.save(reservable);
        }).orElseGet(() -> {
            newReservable.setId(id);
            return repository.save(newReservable);
        });

    }

    /**
     * @param id
     * @return the deleted reservable accoridng to their user id.
     */
    @DeleteMapping("/Reservable/{id}")
    public  void delete(@PathVariable Long id) {

        repository.deleteById(id);
    }



}
