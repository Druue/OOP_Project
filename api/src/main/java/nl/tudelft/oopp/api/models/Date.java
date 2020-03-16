package nl.tudelft.oopp.api.models;

/**
 * A {@link Date}.
 */
public class Date {

    /**
     * The id.
     */
    public Long id;

    /**
     * The year.
     */
    public Integer year;

    /**
     * The month of the year. EXAMPLE: 4 (April in that case).
     */
    public Integer month;

    /**
     * The week of the year. EXAMPLE: 48.
     */
    public Integer week;

    /**
     * The day of the month. EXAMPLE: 28.
     */
    public Integer day;

    /**
     * Whether the date is a holiday or not.
     */
    public boolean isHoliday;

    /**
     * Initialises a new instance of {@link Date}.
     */
    public Date() {

    }

    /**
     * Initialises a new instance of a {@link Date}.
     *
     * @param year      The year.
     * @param month     The month.
     * @param week      The week.
     * @param day       The day.
     * @param isHoliday Whether it's a holiday or not.
     */
    public Date(Integer year, Integer month, Integer week, Integer day, boolean isHoliday) {
        this.year = year;
        this.month = month;
        this.week = week;
        this.day = day;
        this.isHoliday = isHoliday;
    }

    public Date(Long id, Integer year, Integer month, Integer week, Integer day, boolean isHoliday) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.week = week;
        this.day = day;
        this.isHoliday = isHoliday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public void setHoliday(boolean holiday) {
        isHoliday = holiday;
    }
}
