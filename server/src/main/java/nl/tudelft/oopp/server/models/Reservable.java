package nl.tudelft.oopp.server.models;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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


    /**
     * This is a details entity that tells you information about a reservable.
     */
    @OneToOne
    @ElementCollection
    @JoinColumn(name = "details")
    public Collection<Details> details;


    /**
     * The reservable's unique Id.
     */
    @Id
    @Column(name = "id")
    public Long id;

    public Reservable(Collection<Details> details, Long id) {
        this.details = details;
        this.id = id;
    }

    // Add a map that has a onetomany relationship to a list of timeslots List<Timeslot>.
    // Put the timemslots table back and have the one to many relationship to it.



}
