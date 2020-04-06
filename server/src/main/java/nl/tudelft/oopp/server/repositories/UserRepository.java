package nl.tudelft.oopp.server.repositories;

import nl.tudelft.oopp.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);

    boolean existsByEmail(String email);

    boolean existsById(Long userID);

    boolean existsByUsername(String username);

    User findByEmail(String email);

    User findByUsername(String username);
}
