package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.models.Foodcourt;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FoodCourtRepository extends JpaRepository<Foodcourt, Long> {
=======
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * interface to implement crud operations for food court.
 */
@Repository
public interface FoodcourtRepository extends CrudRepository<Foodcourt, Integer> {
>>>>>>> f84d993b60db840bb42d2351cec23fb3c20cce1a
}
