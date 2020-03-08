package nl.tudelft.oopp.server.repositories;

import nl.tudelft.oopp.server.models.TimeSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * interface to implement curd operations for a list of time slots.
 */
@Repository
public interface TimeSlotsRepository extends JpaRepository<TimeSlots, Long> {
}
