package nl.tudelft.oopp.demo.repositories;
import nl.tudelft.oopp.demo.models.Foodcourt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FoodCourtRepository extends JpaRepository<Foodcourt, Integer>{
}
