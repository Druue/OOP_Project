package nl.tudelft.oopp.server.repositories;

import nl.tudelft.oopp.server.models.AvailableTimeSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * interface for Available Time Slots to implement crud operations.
 */
@Repository
public interface AvailableTimeSlotsRepository extends JpaRepository<AvailableTimeSlots, Long> {
}
