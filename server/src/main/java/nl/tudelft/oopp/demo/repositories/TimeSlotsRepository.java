package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.models.TimeSlots;
import org.springframework.data.repository.CrudRepository;

/**
 * interface to implement curd operations for a list of time slots
 */
public interface TimeSlotsRepository extends CrudRepository<TimeSlots, Long> {
}
