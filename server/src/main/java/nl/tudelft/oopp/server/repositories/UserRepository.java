package nl.tudelft.oopp.server.repositories;

import nl.tudelft.oopp.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * This is the repository for Users.
 */
public interface UserRepository extends JpaRepository<User, Long> {


}
