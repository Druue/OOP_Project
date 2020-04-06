package nl.tudelft.oopp.server.repositories;

import nl.tudelft.oopp.server.models.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * interface for bike to implement crud operations.
 */
@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {
}
