package nl.tudelft.oopp.server.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A {@link Date}.
 */
@Entity
public class Date {

    /**
     * The year.
     */
    @Id
    @Column(name = "year")
    public Date year;

    /**
     * The month of the year. EXAMPLE: 4 (April in that case).
     */
    @Column(name = "month")
    public Integer month;

    /**
     * The week of the year. EXAMPLE: 48.
     */
    @Column(name = "week")
    public Integer week;

    /**
     * The day of the month. EXAMPLE: 28.
     */
    @Column(name = "day")
    public Integer day;

    /**
     * Whether the date is a holiday or not.
     */
    @Column(name = "isHoliday")
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
    public Date(Date year, Integer month, Integer week, Integer day, boolean isHoliday) {
        this.year = year;
        this.month = month;
        this.week = week;
        this.day = day;
        this.isHoliday = isHoliday;
    }
}
