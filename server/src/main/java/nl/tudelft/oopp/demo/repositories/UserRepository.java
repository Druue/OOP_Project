package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, String> {

    Users findByNetIdAndPassword(String userID, String password);

    boolean existsByEmail(String email);

    boolean existsByNetId(String netID);


}
