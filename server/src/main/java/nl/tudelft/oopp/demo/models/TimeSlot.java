package nl.tudelft.oopp.demo.models;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * TimeSlot.
 */
public class TimeSlot {

    /**
     * Initialises a new instance of {@link TimeSlot}.
     */
    public TimeSlot() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;

    @Column(name = "start")
    Timestamp startTime;

    @Column(name = "end")
    Timestamp endTime;
}
