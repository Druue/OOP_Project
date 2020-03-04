package nl.tudelft.oopp.demo.models;

import org.springframework.data.repository.CrudRepository;

/**
 * interface for reservable to implement crud operations
 */
public interface ReservableRepository extends CrudRepository<Reservable, Long> {
}
