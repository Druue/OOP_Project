package nl.tudelft.oopp.demo.database_queries;

import nl.tudelft.oopp.demo.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class LoginService {

    public static String userValidate(String NetID, String password) throws AuthenticationException {


        /*
        * Here goes the code that checks whether a user
        * exists in the database with the provided NetID , password and role.
        * If it does not, the method should throw new AuthenticationException()
        * */

        // Return the user's email to be processed by the controller

        return null;
    }
}
