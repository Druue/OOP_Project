package nl.tudelft.oopp.demo.models;

import org.springframework.data.repository.CrudRepository;

/**
 * interface to for foodcourt to implement crud operations
 */
public interface FoodCourtRepository extends CrudRepository<Foodcourt, Integer> {
}
