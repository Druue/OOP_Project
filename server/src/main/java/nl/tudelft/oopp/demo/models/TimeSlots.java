package nl.tudelft.oopp.demo.models;

import java.sql.Timestamp;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Id;

/**
 * TimeSlots.
 */
public class TimeSlots {

    /**
     * Initialises a new {@link TimeSlots}.
     */
    public TimeSlots() {
    }

    // @Id
    // @Column(name = "day")
    // Timestamp day;

    // @ElementCollection
    // @Column(name = "timeslots")
    // Collection<TimeSlot> timeSlots;

}
