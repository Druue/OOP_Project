package nl.tudelft.oopp.server.repositories;

import nl.tudelft.oopp.server.models.TimeSlot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * interface to implement crud operations for timeSlot.
 */
@Repository
public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long> {
}
