package nl.tudelft.oopp.demo.repositories;

import java.util.List;
import nl.tudelft.oopp.demo.models.Building;
import org.springframework.data.repository.CrudRepository;

/**
 * interface to implement crud operations for buildings.
 */
public interface BuildingRepository extends CrudRepository<Building, Integer> {

    List<Building> findAll();

}
