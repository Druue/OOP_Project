package nl.tudelft.oopp.demo.repositories;
import nl.tudelft.oopp.demo.models.Reservable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * interface for reservable to implement crud operations.
 */
@Repository
public interface ReservableRepository extends JpaRepository<Reservable, Long> {

}
