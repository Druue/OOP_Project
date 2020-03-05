package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.models.Building;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * interface to implement curd operations for buildings
 */
public interface BuildingRepository extends CrudRepository<Building, Integer> {

    List<Building> findAll();

}
