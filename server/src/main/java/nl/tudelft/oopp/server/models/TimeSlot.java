package nl.tudelft.oopp.server.models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * TimeSlot.
 */
@Entity
@Table(name = "timeslot")
public class TimeSlot {

    /**
     * Initialises a new instance of {@link TimeSlot}.
     */
    public TimeSlot() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Column(name = "start")
    Timestamp startTime;

    @Column(name = "end")
    Timestamp endTime;

    @Column(name = "availability")
    Boolean isAvailable;
}
