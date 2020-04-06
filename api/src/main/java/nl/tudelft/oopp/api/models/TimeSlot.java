package nl.tudelft.oopp.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * TimeSlot.
 */
public class TimeSlot {

    /**
     * This is the timeslot id.
     */
    private Long id;

    /**
     * This is the index? What did you guys mean buy this?.
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp startTime;

    /**
     * This tells us whether or not the timeslot is available.
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp endTime;

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
    
    /**
     * Initialises a new instance of {@link TimeSlot}.
     * @param id The id of the TimeSlot.
     * @param startTime The starting time.
     * @param endTime The end time.
     */
    public TimeSlot(Long id, Timestamp startTime, Timestamp endTime) {
        this.id = id;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSlot timeSlot = (TimeSlot) o;
        return Objects.equals(getId(), timeSlot.getId())
               && Objects.equals(getStartTime(), timeSlot.getStartTime())
               && Objects.equals(getEndTime(), timeSlot.getEndTime());
    }

}
