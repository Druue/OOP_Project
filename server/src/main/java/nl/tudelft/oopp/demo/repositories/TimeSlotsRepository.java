package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.models.TimeSlots;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * interface to implement curd operations for a list of time slots.
 */
@Repository
public interface TimeSlotsRepository extends CrudRepository<TimeSlots, Long> {
}
