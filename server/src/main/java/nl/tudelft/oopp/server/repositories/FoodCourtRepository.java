package nl.tudelft.oopp.server.repositories;

import nl.tudelft.oopp.server.models.FoodCourt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * interface to implement crud operations for food court.
 */
@Repository
public interface FoodCourtRepository extends JpaRepository<FoodCourt,Integer> {
}
