package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    User findByNetIdAndPassword(String userID, String password);

    boolean existsByEmail(String email);

    boolean existsByNetId(String netID);


}
