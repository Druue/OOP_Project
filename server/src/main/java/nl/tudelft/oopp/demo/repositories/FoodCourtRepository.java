package nl.tudelft.oopp.demo.repositories;
import nl.tudelft.oopp.demo.entities.FoodCourt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCourtRepository extends JpaRepository<FoodCourt, Integer>{
}
