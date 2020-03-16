package nl.tudelft.oopp.api.models;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class TestOpeningTimes {
    private TestHourAndMinutes openingHour;
    private TestHourAndMinutes closingHour;

    public TestOpeningTimes(Timestamp openingHour, Timestamp closingHour) {

        this.openingHour = convertToHoursAndMinutes(openingHour);
        this.closingHour = convertToHoursAndMinutes(closingHour);
    }

    public TestOpeningTimes(TestHourAndMinutes openingHour, TestHourAndMinutes closingHour) {
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    private TestHourAndMinutes convertToHoursAndMinutes(Timestamp timestamp) {
        int minutes = (int) timestamp.getTime() / (1000*60);
        return new TestHourAndMinutes(minutes/60, minutes%60);
    }


    public TestHourAndMinutes getOpeningHour() {
        return openingHour;
    }

    public TestHourAndMinutes getClosingHour() {
        return closingHour;
    }
}
