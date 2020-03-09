package nl.tudelft.oopp.server.repositories;

import nl.tudelft.oopp.server.models.Reservable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**interface to implement crud for reservable by NAME as ID.
 *
 */
@Repository
public interface ReservableRepositoryByName extends JpaRepository<Reservable, String> {
}
