package nl.tudelft.oopp.demo.repositories;
import nl.tudelft.oopp.demo.entities.AvailableTimeSlots;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableTimeSlotsRepository extends JpaRepository<AvailableTimeSlots, Integer> {
}
