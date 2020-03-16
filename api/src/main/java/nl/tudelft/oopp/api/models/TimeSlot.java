package nl.tudelft.oopp.api.models;

import java.sql.Timestamp;

public class TimeSlot {
    Timestamp startTime;
    Timestamp endTime;

    public TimeSlot(Timestamp startTime, Timestamp endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }
}
