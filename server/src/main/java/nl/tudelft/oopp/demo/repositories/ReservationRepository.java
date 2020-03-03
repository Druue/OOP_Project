package nl.tudelft.oopp.demo.repositories;
import nl.tudelft.oopp.demo.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
}
