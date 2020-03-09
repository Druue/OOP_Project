package nl.tudelft.oopp.server.controllers;

import com.google.gson.Gson;
import javax.naming.AuthenticationException;
import nl.tudelft.oopp.api.models.LoginRequest;
import nl.tudelft.oopp.api.models.ServerResponseAlert;
import nl.tudelft.oopp.server.services.LoggerService;
import nl.tudelft.oopp.server.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// This class contains the method to answer to a login request from the client
// It receives as input the provided by the user NetID and a hashed password.
@RestController
public class LoginController {

    private final LoginService service;

    @Autowired
    public LoginController(LoginService service) {
        this.service = service;
    }

    /**
     * This method logs in a user to the application with provided
     * NetID and password. It calls the method userValidate() of the LoginService class to
     * query the database for the user.
     *
     * @param request - The request provided by the user details mapped to a LoginDetails object
     *                through the @RequestBody annotation.
     * @return An instance of ResponseEntity with status code 200 if the user is successfully authenticated.
     *              Otherwise returns Bad Request response.
     */
    @PostMapping(value = "login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ServerResponseAlert> validateAuthentication(@RequestBody String request) {

        Gson gson = new Gson();
        LoginRequest loginRequest = gson.fromJson(request, LoginRequest.class);
        try {
            String type = service.userValidate(loginRequest);
            LoggerService.info(LoginController.class, "User successfully authenticated.");

            // Send a response containing a success message, and the user's type.
            ServerResponseAlert a = new ServerResponseAlert("Successful login!", "CONFIRMATION");
            return ResponseEntity.ok().body(a);
        } catch (AuthenticationException e) {
            LoggerService.info(LoginController.class, "Authentication failed for user with NetID: "
                                                      + loginRequest.getNetID() + " and password "
                                                      + loginRequest.getPassword()
                                                      + " : No such user registered.");

            // Send a response containing an error message.
            ServerResponseAlert a = new ServerResponseAlert("Invalid user/password combination.", "ERROR");
            return ResponseEntity.badRequest().body(a);
        }
    }


}
