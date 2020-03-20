package nl.tudelft.oopp.server.repositories;

import java.util.List;
import nl.tudelft.oopp.server.models.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * interface for building to implement crud operations.
 */
@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {

    List<Building> findAll();

    List<BuildingsDetails> findAllBy();

    List<BuildingNumber> getAllBy();

    boolean existsByNumber(Long number);

}
