package nl.tudelft.oopp.server.services;

import javassist.NotFoundException;
import javax.naming.AuthenticationException;
import nl.tudelft.oopp.server.models.AuthorizationException;
import nl.tudelft.oopp.server.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    public static final String NOT_ADMIN =
        "Unauthorized request. The requesting user is not an administrator.";

    public static final String NO_USER_FOUND =
        "Authentication for user failed. No administrator with that name found.";

    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(AuthorizationService.class);

    public AuthorizationService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Makes an authorization check on a user requesting something.
     *
     * @param username The username of the user to validate.
     * @throws AuthorizationException  Throws it if the user exists
     *                                 but his role is not Admin.
     * @throws AuthenticationException Throws it if a user with that username is not found.
     */
    public void checkAuthorization(String username)
        throws AuthorizationException, AuthenticationException {

        User userToBeAuthenticated = authenticateUser(username);
        logger.info("Checking authorization of user with username: " + username + " ...");
        if (userToBeAuthenticated.userKind != nl.tudelft.oopp.server.models.UserKind.Admin) {
            logger.error("Authorization failed. No Administrator with username "
                + username + "found");
            throw new AuthorizationException();
        }
        logger.info("Successful authorization for user with username: " + username + ".");
    }


    /** Authenticates a user by finding him in the database by his username and returns
     *      his data in a {@link User} object.
     * @param username The username of the user to find in the database.
     * @return A {@link User} object representing the found user in the database.
     * @throws AuthenticationException Throws it if the user is not found.
     */
    public User authenticateUser(String username) throws AuthenticationException {
        try {
            logger.info("Authenticating user with username: " + username + " ...");
            User user = userService.getUserByUsername(username);
            logger.info("Authentication successful for user with username: " + username + ".");
            return user;
        } catch (NotFoundException e) {
            logger.error("Authentication failed for user with username: " + username
                    + ". No such user found.");
            throw new AuthenticationException();
        }
    }
}
