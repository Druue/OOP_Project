package nl.tudelft.oopp.demo.repositories;
import nl.tudelft.oopp.demo.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
}
