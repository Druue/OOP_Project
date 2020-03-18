package nl.tudelft.oopp.server.services;

import javassist.NotFoundException;
import javax.naming.AuthenticationException;
import nl.tudelft.oopp.api.models.UserKind;
import nl.tudelft.oopp.server.models.AuthorizationException;
import nl.tudelft.oopp.server.models.User;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private UserService userService;

    public AuthorizationService() {
    }

    /** Makes an authorization check on a user requesting something.
     * @param username The username of the user to validate.
     * @param role The provided by the user role to validate.
     * @throws AuthorizationException Throws it if role is not Admin or the user exists
     *      but his role is not Admin.
     * @throws AuthenticationException Throws it if a user with that username is not found.
     */
    public void checkAuthorization(String username, UserKind role)
        throws AuthorizationException, AuthenticationException {

        if (role != UserKind.Admin) {
            throw new AuthorizationException();
        }
        try {
            User userToBeAuthenticated = userService.getUserByUsername(username);
            if (userToBeAuthenticated.userKind != nl.tudelft.oopp.server.models.UserKind.Admin) {
                throw new AuthorizationException();
            }
        } catch (NotFoundException e) {
            throw new AuthenticationException();
        }
    }
}
