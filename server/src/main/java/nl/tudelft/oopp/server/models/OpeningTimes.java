package nl.tudelft.oopp.server.models;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The {@link OpeningTimes}.
 */
@Entity
@Table(name = "OpeningTimes")
public class OpeningTimes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "timeslots_id", insertable = false, updatable = false)
    private Long id;

    /**
     * This tells you when the building opens.
     */
    @Column(name = "opening_hour")
    public TimeSlot openingHour;

    /**
     * This tells you when the building closes.
     */
    @Column(name = "closing_hour")
    public TimeSlot closingHour;

    /**
     * This tells you the dates that the building is open.
     */
    @OneToOne
    @ElementCollection
    public Collection<Date> dates;

    /**
     * Initialises a new {@link OpeningTimes}.
     */
    public OpeningTimes() {

    }

    /**
     * Initialises a new {@link OpeningTimes}.
     * 
     * @param openingHour When the building opens.
     * @param closingHour When the building closes.
     * @param dates       The dates on which the building is open.
     */
    public OpeningTimes(TimeSlot openingHour, TimeSlot closingHour, Collection<Date> dates) {
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.dates = dates;
    }
}
