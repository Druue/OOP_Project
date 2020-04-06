package nl.tudelft.oopp.server.services;

import java.util.List;
import javassist.NotFoundException;
import nl.tudelft.oopp.server.models.User;
import nl.tudelft.oopp.server.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

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

    /**method to find a user by username.
     **
     * @param username linked with a user
     * @return a user or null if it does not exist
     */
    public User getUserUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        logger.info("Fetching all users in the database ...");
        return userRepository.findAll();
    }
}
