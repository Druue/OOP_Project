package nl.tudelft.oopp.server.models;

import com.fasterxml.jackson.annotation.JsonFormat;
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
     * This is the timeslot id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long id;

    /**
     * This is the index? What did you guys mean buy this?.
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "start")
    public Timestamp startTime;

    /**
     * This tells us whether or not the timeslot is available.
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "end")
    public Timestamp endTime;

    /**
     * Initialises a new instance of {@link TimeSlot}.
     */
    public TimeSlot() {

    }

    /**
     * Initialises a new instance of {@link TimeSlot}.
     * 
     * @param startTime When the timeslot starts.
     * @param endTime   When the timeslot ends.
     */
    public TimeSlot(Timestamp startTime, Timestamp endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
