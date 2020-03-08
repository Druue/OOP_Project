package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.models.AvailableTimeSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * interface for Available Time Slots to implement crud operations.
 */
@Repository
public interface AvailableTimeSlotsRepository extends JpaRepository<AvailableTimeSlots, Long> {
}
