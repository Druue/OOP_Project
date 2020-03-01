package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Reservable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservableRepository extends JpaRepository<Reservable, String> {
}
