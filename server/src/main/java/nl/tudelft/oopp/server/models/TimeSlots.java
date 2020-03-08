package nl.tudelft.oopp.server.models;

import javax.persistence.*;
import java.util.Collection;

/**
 * TimeSlots.
 */
@Entity
@Table(name = "timeslots")
public class TimeSlots {

    /**
     * Initialises a new {@link TimeSlots}.
     */
    public TimeSlots() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "timeslots_id", insertable = false, updatable = false)
    private Long id;

    @ElementCollection
    @Column(name = "timeslots_view")
    Collection<TimeSlot> timeSlots;

}
