package nl.tudelft.oopp.server.services;

import javax.naming.AuthenticationException;
import nl.tudelft.oopp.api.models.LoginRequest;
import nl.tudelft.oopp.server.models.User;
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
        String username = providedDetails.getUsername();
        String password = providedDetails.getPassword();

        User userToBeValidated = userRepository.findByUsernameAndPassword(username, password);

        if (userToBeValidated == null) {
            throw new AuthenticationException();
        }
        String userEmail = userToBeValidated.email;
        String domainEmailPart = userEmail.split("@")[1];
        String userRole = domainEmailPart.split(".")[0];

        return userRole;
    }

    public User getUserInformation(LoginRequest providedDetails) {
        return userRepository.findByUsername(providedDetails.getUsername());
    }
}
