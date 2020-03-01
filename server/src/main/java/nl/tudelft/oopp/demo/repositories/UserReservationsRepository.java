package nl.tudelft.oopp.demo.repositories;
import nl.tudelft.oopp.demo.entities.UsersReservations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReservationsRepository extends JpaRepository<UserReservations, Integer>{
}
