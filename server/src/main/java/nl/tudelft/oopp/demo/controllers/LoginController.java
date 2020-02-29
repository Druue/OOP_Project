package nl.tudelft.oopp.demo.controllers;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;

import nl.tudelft.oopp.demo.models.LoginDetails;
import nl.tudelft.oopp.demo.models.LoginResponse;
import nl.tudelft.oopp.demo.services.LoggerService;
import nl.tudelft.oopp.demo.services.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// This class contains the method to answer to a login request from the client
// It receives as input the provided by the user NetID and a hashed password.
@RestController
public class LoginController {

    private LoginService service;

    @Autowired
    public LoginController(LoginService service) {
        this.service = service;
    }

    /**
     * This method logs in a user to the application with provided
     * NetID and password. It calls the method userValidate() of the LoginService class to
     * query the database for the user.
     * @param providedDetails - The provided by the user details mapped to a LoginDetails object
     *                        through the @RequestBody annotation.
     * @param newUserSession  - The user session, which is automatically created by the Spring Session module
     * @return An instance of ResponseEntity with status code 200 if the user is successfully authenticated.
     *              Otherwise returns Bad Request response.
     */
    @PostMapping(value = "login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoginResponse> validateAuthentication(@RequestBody LoginDetails providedDetails,
                                                                HttpSession newUserSession) {

        try {
            String role = service.userValidate(providedDetails);
            LoggerService.info(LoginController.class, "User successfully authenticated.");

            //TODO: Work with sessions
            newUserSession.setAttribute("NetID", providedDetails.getNetID());
            newUserSession.setAttribute("Role", role);

            // Send a response containing a success message, and the user's role.
            LoginResponse a = new LoginResponse("Successful login!", "CONFIRMATION", role);
            return ResponseEntity.ok().body(a);
        } catch (AuthenticationException e) {
            LoggerService.info(LoginController.class,"Authentication failed for user with NetID: "
                   + providedDetails.getNetID() + " and password " + providedDetails.getPassword()
                                                    + " : No such user registered.");

            // Send a response containing an error message.
            LoginResponse a = new LoginResponse("Invalid user/password combination.", "ERROR", null);
            return ResponseEntity.badRequest().body(a);
        }
    }
}
