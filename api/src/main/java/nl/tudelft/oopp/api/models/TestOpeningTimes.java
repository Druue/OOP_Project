package nl.tudelft.oopp.api.models;

import java.sql.Timestamp;

public class TestOpeningTimes {
    private TestHourAndMinutes openingHour;
    private TestHourAndMinutes closingHour;

    /**
     * A constructor for {@link TestOpeningTimes}.
     * @param openingHour the opening hour.
     * @param closingHour the closing hour.
     */
    public TestOpeningTimes(Timestamp openingHour, Timestamp closingHour) {

        this.openingHour = convertToHoursAndMinutes(openingHour);
        this.closingHour = convertToHoursAndMinutes(closingHour);
    }

    public TestOpeningTimes(TestHourAndMinutes openingHour, TestHourAndMinutes closingHour) {
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    /**
     * Converts a Timestamp to a {@link TestHourAndMinutes}.
     * @param timestamp the Timestamp to convert.
     * @return a {@link TestHourAndMinutes} object.
     */
    private TestHourAndMinutes convertToHoursAndMinutes(Timestamp timestamp) {
        int minutes = (int) timestamp.getTime() / (1000 * 60);
        return new TestHourAndMinutes(minutes / 60, minutes % 60);
    }


    public TestHourAndMinutes getOpeningHour() {
        return openingHour;
    }

    public TestHourAndMinutes getClosingHour() {
        return closingHour;
    }
}
