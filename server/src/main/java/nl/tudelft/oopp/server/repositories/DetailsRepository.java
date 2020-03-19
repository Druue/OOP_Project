package nl.tudelft.oopp.server.repositories;

import nl.tudelft.oopp.server.models.Details;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailsRepository extends JpaRepository<Details, Long> {

    boolean existsByName(String name);
}
