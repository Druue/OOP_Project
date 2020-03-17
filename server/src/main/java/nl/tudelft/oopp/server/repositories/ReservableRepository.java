package nl.tudelft.oopp.server.repositories;

import java.util.List;
import nl.tudelft.oopp.server.models.Reservable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * interface for reservable to implement crud operations.
 */
@Repository
public interface ReservableRepository extends JpaRepository<Reservable, Long> {
    List<Reservable> findByDetails_Name(String name);
}
