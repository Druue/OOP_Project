package nl.tudelft.oopp.demo.models;

import org.springframework.data.repository.CrudRepository;

/**
 * interface for timeSlots to implement crud operations
 */
public interface TimeSlotsRepository extends CrudRepository<TimeSlots, Long> {
}
