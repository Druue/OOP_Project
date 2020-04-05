package nl.tudelft.oopp.server.controllers;

import java.util.List;
import javax.naming.AuthenticationException;
import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.server.models.AuthorizationException;
import nl.tudelft.oopp.server.models.User;
import nl.tudelft.oopp.server.services.AuthorizationService;
import nl.tudelft.oopp.server.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private static final String NOT_ADMIN =
        "Unauthorized request. The requesting user is not an administrator.";

    private static final String NO_USER_FOUND =
        "Authentication for user failed. No administrator with that name found.";

    private final AuthorizationService authorizationService;
    private final UserService userService;

    public UserController(AuthorizationService authorizationService, UserService userService) {
        this.authorizationService = authorizationService;
        this.userService = userService;
    }

    /**     Receives a POST request for all users. First checks whether the requesting user is
     *      existing and whether he is an administrator using the {@link AuthorizationService} bean.
     *      If the checks passes, uses the {@link UserService} bean to fetcg the list of all users
     *      and send it in the response body.
     * @param request   The {@link ClientRequest} object containing the username to check for
     *                  existance.
     * @return          A {@link ResponseEntity} object containing a {@link List} of {@link User}
     *                  objects representing the existing users in the database.
     */
    @PostMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(ClientRequest<String> request) {

        logger.info("Received POST request for all users in the database. Processing ...");

        try {
            authorizationService.checkAuthorization(request.getUsername());
        } catch (AuthorizationException e) {
            logger.error(NOT_ADMIN);
            return ResponseEntity.badRequest().build();
        } catch (AuthenticationException e) {
            logger.error(NO_USER_FOUND);
            return ResponseEntity.badRequest().build();
        }

        logger.info("User with username " + request.getUsername() + " successfully found.");

        List<User> responseList = userService.getAllUsers();

        logger.info("Sending the list of users ...");
        return ResponseEntity.ok(responseList);
    }
}
