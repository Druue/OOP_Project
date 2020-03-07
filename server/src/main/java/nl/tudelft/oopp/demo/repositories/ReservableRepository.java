package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.models.Reservable;
import org.springframework.data.repository.CrudRepository;

/**
 * interface for to implement crud operations for reservable.
 */
public interface ReservableRepository extends CrudRepository<Reservable, Long> {
}
