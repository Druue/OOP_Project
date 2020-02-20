package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.database_queries.LoginQuery;
import nl.tudelft.oopp.demo.models.LoginDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.ArrayList;

@Controller
public class LoginController {

    /*
    * POST endpoint to validate authentication of a user
    * The class provides a method to find the user-provided details in the database
    * 1) If it finds them , it notifies the user that the login with message true
    * 2) If it does not find it, it sends a message false
    * */

    @PostMapping("login")
    @ResponseBody
    public boolean validateAuthentication(@RequestBody LoginDetails loginDetails ) {

        // Connect to the database and establish
        // whether a user with that NetID and password exists
        // 1) The user exists - send response true
        // 2) There is no such password or NetID - send response false

        // Get the login details
        String NetID = loginDetails.getNetID();
        String password = loginDetails.getPassword();

       try {
           LoginQuery.userValidate(NetID , password);
       }
       catch (AuthenticationException e) {
           System.out.println("Authentication failed for user with NetID: " + NetID + " and password " + password );
           return false;
       }
        return true;
    }

}
