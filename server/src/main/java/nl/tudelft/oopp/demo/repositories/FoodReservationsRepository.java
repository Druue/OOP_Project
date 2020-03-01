package nl.tudelft.oopp.demo.repositories;
import nl.tudelft.oopp.demo.entities.FoodReservations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodReservationsRepository extends JpaRepository<FoodReservations, Integer>{
}
