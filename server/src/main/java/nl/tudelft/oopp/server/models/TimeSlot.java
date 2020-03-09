package nl.tudelft.oopp.server.models;

import java.sql.Timestamp;
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
