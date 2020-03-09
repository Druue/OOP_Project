package nl.tudelft.oopp.server.repositories;

import nl.tudelft.oopp.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserIdAndPassword(Long userID, String password);

    boolean existsByEmail(String email);

    boolean existsByUserId(Long userID);

    User findByEmail(String email);

    User findByUsername(String username);
}
