package nl.tudelft.oopp.demo.repositories;
import nl.tudelft.oopp.demo.models.UsersReservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReservationsRepository extends JpaRepository<UserReservations, Long>{
}
