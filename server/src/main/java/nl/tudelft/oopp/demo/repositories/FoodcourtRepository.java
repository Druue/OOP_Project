package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.models.Foodcourt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * interface to implement crud operations for food court.
 */
@Repository
public interface FoodcourtRepository extends CrudRepository<Foodcourt, Integer> {
}
