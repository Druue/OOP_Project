package nl.tudelft.oopp.demo.models;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OpeningTimes
 */
@Entity
@Table(name = "OpeningTimes")
public class OpeningTimes {

    /**
     * Initialises a new {@link OpeningTimes}.
     */
    public OpeningTimes() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "timeslots_id", insertable = false, updatable = false)
    private Long id;

    /**
     * This tells you when the building opens.
     */
    @Column(name="opening_hour")
    public TimeSlot openingHour;

    /**
     * This tells you when the building closes.
     */
    @Column(name="closing_hour")
    public TimeSlot closingHour;

    /**
     * This tells you the dates that the building is open.
     */
    @OneToOne
    @ElementCollection
    public Collection<Date> date;


}
