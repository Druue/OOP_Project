package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.models.TimeSlot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * interface to implement crud operations for timeSlot.
 */
@Repository
public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long> {
}
