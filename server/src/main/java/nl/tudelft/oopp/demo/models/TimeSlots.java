package nl.tudelft.oopp.demo.models;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.ElementCollection;

/**
 * TimeSlots
 */
public class TimeSlots {

	/**
	 * Initialises a new {@link TimeSlots}
	 */
	public TimeSlots() {
	}

	@ElementCollection
	@Column(name = "timeslots")
	Collection<TimeSlot> timeSlots;

}
