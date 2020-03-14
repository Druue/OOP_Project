package nl.tudelft.oopp.demo.models;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Date {

    public Date() {
    }

    /**
     * This represents a year in java date object.
     */
    @Column(name="year")
    public Date year;

    /**
     * This is a month of a year.
     */
    @Column(name="month")
    public Integer month;

    /**
     * This is the day of the month.
     */
    @Column(name="day")
    public Integer day;

    /**
     * This is the week of the year according to the Dutch calendar.
     */
    @Column(name="week")
    public Integer week;

    /**
     * This tells us if it is a holiday according to the Dutch calendar.
     */
    @Column(name="isHoliday")
    public boolean isHoliday;


    /**
     * @param year
     * @param month
     * @param week
     * @param day
     * @param isHoliday
     */
    public Date(Date year, Integer month, Integer week, Integer day, boolean isHoliday) {
        this.year= new Date();
        this.month=month;
        this.week=week;
        this.day=day;
        this.isHoliday=isHoliday;


    }


}
