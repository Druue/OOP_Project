package nl.tudelft.oopp.server.controllers;

import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.LoginRequest;
import nl.tudelft.oopp.api.models.User;
import nl.tudelft.oopp.api.models.UserAuthResponse;
import nl.tudelft.oopp.server.services.LoggerService;
import nl.tudelft.oopp.server.services.LoginService;
import nl.tudelft.oopp.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// This class contains the method to answer to a login request from the client
// It receives as input the provided by the user username and a hashed password.

@RestController
public class LoginController {

    public HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

    private final LoginService service;
    private final UserService userService;

    @Autowired
    public LoginController(LoginService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    /**
     * This method logs in a user to the application with provided username and password. It calls
     * the method userValidate() of the LoginService class to query the database for the user.
     *
     * @param loginRequest - The request provided by the user details mapped to a LoginDetails object
     *                through the @RequestBody annotation.
     * @return An instance of ResponseEntity with status code 200 if the user is successfully
     *         authenticated. Otherwise returns Bad Request response.
     */
    @PostMapping(value = "login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserAuthResponse> validateAuthentication(@RequestBody LoginRequest loginRequest) {

        nl.tudelft.oopp.server.models.User test = userService.getUserUserName(loginRequest.getUsername());
        if (test != null) {
            if (!test.password.equals(loginRequest.getPassword())) {
                LoggerService.info(RegistrationController.class, "username/password combination wrong");
                return ResponseEntity.badRequest().body(
                        new UserAuthResponse("username/password combination wrong", "ERROR", null));
            }
        }
        try {
            // Gets a complete User entity from the database, to send back to the client for later use.
            User user = httpRequestHandler.convertModel(service.getUserInformation(loginRequest),
                    User.class);
            LoggerService.info(LoginController.class, "User successfully authenticated.");
            LoggerService.info(LoginController.class, user.getUsername() + user.getEmail());

            // Send a response containing a success message, and the user's type.
            UserAuthResponse response = new UserAuthResponse("Successful login!", "CONFIRMATION", user);
            return ResponseEntity.ok().body(response);

        } catch (Exception /* AuthenticationException */ e) {
            LoggerService.info(LoginController.class,
                    "Authentication failed for user with username: " + loginRequest.getUsername()
                            + " and password " + loginRequest.getPassword()
                            + " : No such user registered.");

            // Send a response containing an error message.
            UserAuthResponse response =
                    new UserAuthResponse("Invalid user/password combination.", "ERROR", null);
            return ResponseEntity.badRequest().body(response);
        }
    }


}

