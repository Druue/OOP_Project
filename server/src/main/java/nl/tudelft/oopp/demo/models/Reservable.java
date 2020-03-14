package nl.tudelft.oopp.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Initialises a new {@link Reservable}.
 */
@Entity
@Table(name = "Reservable")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Reservable {

    /**
     * Initialises a new instance of {@link Reservable}.
     */
    public Reservable() {
    }

    public Reservable(Details details, Long id) {
        this.details=details;
        this.id = id;
    }

    /**
     * This is a details entity that tells you information about a reservable
     */
    @OneToMany
    @Column(name = "details")
    private Details details;


    /**
     * The reservable's unique Id.
     */
    @Id
    @Column(name = "id")
    public Long id;

    // Add a map that has a onetomany relationship to a list of timeslots List<Timeslot>.
    //Put the timemslots table back and have the one to many relationship to it.



}
