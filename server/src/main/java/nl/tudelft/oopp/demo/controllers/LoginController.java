package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.services.LoggerService;
import nl.tudelft.oopp.demo.services.LoginService;
import nl.tudelft.oopp.demo.models.LoginDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;


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
     * @param providedDetails - The provided by the user details mapped to a
     *                          LoginDetails object through the @RequestBody annotation
     * @param newUserSession  - The user session, which is automatically created by the Spring Session module
     * @return An instance of ResponseEntity<String> with status code 200 if the user is successfully authenticated.
     * Otherwise returns Bad Request response.
     */
    @PostMapping("/login")
    public ResponseEntity<String> validateAuthentication(@RequestBody LoginDetails providedDetails,
                                                              HttpSession newUserSession) {

        String NetID = providedDetails.getNetID();
        String password = providedDetails.getPassword();

        try {
            String role = service.userValidate(NetID , password);

            LoggerService.info(LoginController.class , "User successfully authenticated with NetID: "
                    + NetID + " and role: " + role);
            newUserSession.setAttribute("NetID" , NetID);
            newUserSession.setAttribute("Role" , role);

            return ResponseEntity.status(200).build();
        }
        catch (AuthenticationException e) {

           LoggerService.info(LoginController.class ,"Authentication failed for user with NetID: "
                   + NetID + " and password " + password + " : No such user registered." );

           return ResponseEntity.badRequest().header("Reason" , "No such user").build();
        }

    }

}
