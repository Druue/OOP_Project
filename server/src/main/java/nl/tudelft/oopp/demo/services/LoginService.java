package nl.tudelft.oopp.demo.services;

import nl.tudelft.oopp.demo.models.LoginDetails;
import nl.tudelft.oopp.demo.repositories.QuoteRepository;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class LoginService {

    final
    QuoteRepository repository;

    public LoginService(QuoteRepository repository) {
        this.repository = repository;
    }

    public String userValidate(LoginDetails providedDetails) throws AuthenticationException {
        String NetID = providedDetails.getNetID();
        String password = providedDetails.getPassword();

        /*
        * Here goes the code that checks whether a user
        * exists in the database with the provided NetID , password and role.
        * If it does not, the method should throw new AuthenticationException()
        * */

        // Return the user's email to be processed by the controller

        return null;
    }
}
