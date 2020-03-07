package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUserIdAndAndPassword(String userID , String password);

}
