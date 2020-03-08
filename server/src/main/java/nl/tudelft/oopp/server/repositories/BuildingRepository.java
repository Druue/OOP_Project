package nl.tudelft.oopp.server.repositories;

import nl.tudelft.oopp.server.models.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * interface for building to implement crud operations.
 */
@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
}
