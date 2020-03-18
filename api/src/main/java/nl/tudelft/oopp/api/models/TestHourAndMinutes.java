package nl.tudelft.oopp.api.models;

public class TestHourAndMinutes {
    private int hour;
    private int minutes;

    public TestHourAndMinutes(int hour, int minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }
}
