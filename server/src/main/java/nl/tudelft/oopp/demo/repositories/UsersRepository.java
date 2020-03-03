package nl.tudelft.oopp.demo.repositories;
import nl.tudelft.oopp.demo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
}
