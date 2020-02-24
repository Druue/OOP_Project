package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.database_queries.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    /*
    * POST endpoint to validate authentication of a user
    * The class uses a method userValidate of the LoginQuery class to find the user-provided details in the database
    * 1) If the method finds the user in the database, it returns its role to be sent through the server response
    * 2) If it does not find it, it throws AuthenticationException and this method catches it and sends "None"
    * */
    @PostMapping("/login")
    @ResponseBody
    public String validateAuthentication(
            @RequestParam("NetID") String NetID ,
            @RequestParam("password") String password,
            HttpServletResponse response
            ) {

        String role = null;

        try {
           role = LoginService.userValidate(NetID , password);
        }
        catch (AuthenticationException e) {
           System.out.println("Authentication failed for user with NetID: " + NetID + " and password " + password );
           System.out.println("No such user.");
           return "None";
        }

        // Set the Cookie NetID="Provided NetID" if the login was successful
        response.addCookie(new Cookie("NetID" , NetID));
        return role;
    }

}
