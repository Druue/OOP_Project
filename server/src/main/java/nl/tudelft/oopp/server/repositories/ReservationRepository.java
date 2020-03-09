package nl.tudelft.oopp.server.repositories;

import java.util.List;
import nl.tudelft.oopp.server.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * interface for reservation to implement crud operations.
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUserID(Long userID);


    List<Reservation> findAll();
}
