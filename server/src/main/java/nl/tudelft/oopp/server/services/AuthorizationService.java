package nl.tudelft.oopp.server.services;

import javassist.NotFoundException;
import javax.naming.AuthenticationException;
import nl.tudelft.oopp.server.models.AuthorizationException;
import nl.tudelft.oopp.server.models.User;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final UserService userService;

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
        if (userToBeAuthenticated.userKind != nl.tudelft.oopp.server.models.UserKind.Admin) {
            throw new AuthorizationException();
        }
    }


    /** Authenticates a user by finding him in the database by his username and returns
     *      his data in a {@link User} object.
     * @param username The username of the user to find in the database.
     * @return A {@link User} object representing the found user in the database.
     * @throws AuthenticationException Throws it if the user is not found.
     */
    public User authenticateUser(String username) throws AuthenticationException {
        try {
            return userService.getUserByUsername(username);
        } catch (NotFoundException e) {
            throw new AuthenticationException();
        }
    }
}
