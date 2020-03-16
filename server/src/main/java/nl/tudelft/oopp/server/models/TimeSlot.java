package nl.tudelft.oopp.server.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TimeSlot.
 */
@Entity
@Table(name = "Timeslot")
public class TimeSlot {

    /**
     * This is the timeslot id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long id;

    /**
     * This is the index? What did you guys mean buy this?.
     */
    @Column(name = "index")
    public String index;

    /**
     * This tells us whether or not the timeslot is available.
     */
    @Column(name = "isAvailable")
    public boolean isAvailable;

    /**
     * Initialises a new instance of {@link TimeSlot}.
     */
    public TimeSlot() {

    }

    /**
     * Initialises a new instance of {@link TimeSlot}.
     * 
     * @param id          The timeslot's id.
     * @param index       The timeslot's index.
     * @param isAvailable The availability of the timeslot.
     */
    public TimeSlot(Long id, String index, boolean isAvailable) {
        this.id = id;
        this.index = index;
        this.isAvailable = isAvailable;
    }
}
