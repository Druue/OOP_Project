package nl.tudelft.oopp.server.models;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
