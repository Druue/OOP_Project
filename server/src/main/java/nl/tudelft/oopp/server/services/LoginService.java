package nl.tudelft.oopp.server.services;

import javax.naming.AuthenticationException;
import nl.tudelft.oopp.api.models.LoginRequest;
import nl.tudelft.oopp.server.controllers.LoginController;
import nl.tudelft.oopp.server.models.User;
import nl.tudelft.oopp.server.repositories.ReservationRepository;
import nl.tudelft.oopp.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Validates whether or not a user provided valid login credentials.
     * 
     * @param providedDetails the object containing the user's login credentials.
     * @return the role of the authenticated user.
     * @throws AuthenticationException thrown if the login credentials are invalid.
     */
    public String userValidate(LoginRequest providedDetails) throws AuthenticationException {
        // String netID = providedDetails.getNetID();
        // String password = providedDetails.getPassword();

        /*
         * Here goes the code that checks whether a user exists in the database with the provided
         * NetID , password and role. If it does not, the method should throw new
         * AuthenticationException()
         */

        // Return the user's email to be processed by the controller

        return null;
    }

    public User getUserInformation(LoginRequest providedDetails) {
        return userRepository.findByUsername(providedDetails.getUsername());
    }
}
