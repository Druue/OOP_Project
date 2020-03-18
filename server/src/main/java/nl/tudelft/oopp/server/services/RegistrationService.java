package nl.tudelft.oopp.server.services;

import java.util.Optional;
import javax.management.InstanceAlreadyExistsException;
import nl.tudelft.oopp.server.models.User;
import nl.tudelft.oopp.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    /**
     * This method registers a new user in the database of the application. If the user already
     * exists, throws exception.
     * 
     * @param registrationRequest The user provided registration details
     * @throws InstanceAlreadyExistsException - throws it if a user with the provided username
     *                                        already exists
     */
    public void registerUser(User registrationRequest) throws InstanceAlreadyExistsException {
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public Long getUserId(String email) {
        return userRepository.findByEmail(email).id;
    }

    public Optional<User> getUserByID(Long userId) {
        return userRepository.findById(userId);
    }
}
