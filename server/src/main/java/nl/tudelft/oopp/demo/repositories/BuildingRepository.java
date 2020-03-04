package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.models.Building;
import org.springframework.data.repository.CrudRepository;

/**
 * interface to implement curd operations for buildings
 */
public interface BuildingRepository extends CrudRepository<Building, Integer> {
}
