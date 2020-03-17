package nl.tudelft.oopp.api.models;

import java.sql.Timestamp;

/**
 * TimeSlot.
 */
public class TimeSlot {

    /**
     * This is the timeslot id.
     */
    public Long id;

    /**
     * This is the index? What did you guys mean buy this?.
     */
    public Timestamp startTime;

    /**
     * This tells us whether or not the timeslot is available.
     */
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public TimeSlot(Long id, Timestamp startTime, Timestamp endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
