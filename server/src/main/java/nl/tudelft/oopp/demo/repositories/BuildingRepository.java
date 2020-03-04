package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.models.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
}
