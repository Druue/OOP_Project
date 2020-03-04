package nl.tudelft.oopp.demo.models;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface to for building to implement crud operations
 */

public interface BuidlingRepository extends CrudRepository<Building, Integer> {
}
