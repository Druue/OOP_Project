package nl.tudelft.oopp.demo.models;

import org.springframework.data.repository.CrudRepository;

/**
 * interface for timeslot to implement crud operations
 */
public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long> {
}
