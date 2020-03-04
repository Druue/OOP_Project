package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.models.FoodReservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodReservationsRepository extends JpaRepository<FoodReservations, Long>{
}
