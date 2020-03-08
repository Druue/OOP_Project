package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.models.Reservation;
import org.springframework.data.repository.CrudRepository;

/**interface for reservation to implement crud operations.
 *
 */
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
