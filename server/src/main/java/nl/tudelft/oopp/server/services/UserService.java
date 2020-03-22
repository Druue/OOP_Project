package nl.tudelft.oopp.server.services;

import javassist.NotFoundException;
import nl.tudelft.oopp.server.models.User;
import nl.tudelft.oopp.server.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /** Finds a user in the database with the given username.
     * @param username The username of the user to be found.
     * @return A User object representing the found user.
     * @throws NotFoundException Throws it if no user with the given username exists.
     */
    public User getUserByUsername(String username) throws NotFoundException {
        LoggerService.info(UserService.class,
            "Checking database for user with the provided username");
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new NotFoundException("No such user exists");
        } else {
            return user;
        }
    }

    /**returns a user by email if exists or null otherwise.
     *
     * @param email linked with a user
     * @return a user
     */
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
