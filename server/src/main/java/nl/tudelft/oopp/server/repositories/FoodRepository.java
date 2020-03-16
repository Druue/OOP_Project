package nl.tudelft.oopp.server.repositories;

import nl.tudelft.oopp.server.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * interface for food to implement crud operations.
 */
@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}
