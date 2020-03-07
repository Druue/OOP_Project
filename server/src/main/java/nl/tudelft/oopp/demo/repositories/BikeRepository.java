package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.models.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * interface for bike to implement crud operations.
 */
@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {
}
