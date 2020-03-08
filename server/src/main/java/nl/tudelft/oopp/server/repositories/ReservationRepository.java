package nl.tudelft.oopp.server.repositories;

import nl.tudelft.oopp.server.models.Reservation;
import org.springframework.data.repository.CrudRepository;

/**interface for reservation to implement crud operations.
 *
 */
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
