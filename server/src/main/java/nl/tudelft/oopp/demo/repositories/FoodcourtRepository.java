package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.models.Foodcourt;
import org.springframework.data.repository.CrudRepository;

/**
 * interface to implement crud operations for food court
 */
public interface FoodcourtRepository extends CrudRepository<Foodcourt, Integer> {
}
