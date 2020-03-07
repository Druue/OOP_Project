package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.models.TimeSlot;
import org.springframework.data.repository.CrudRepository;

/**
 * interface to implement crud operations for timeSlot.
 */
public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long> {
}
