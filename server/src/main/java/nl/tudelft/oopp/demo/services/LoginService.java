package nl.tudelft.oopp.demo.services;

import javax.naming.AuthenticationException;
import nl.tudelft.oopp.demo.models.LoginDetails;
import nl.tudelft.oopp.demo.models.User;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    final UserRepository repository;

    public LoginService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Validates whether or not a user provided valid login credentials.
     *
     * @param providedDetails the object containing the user's login credentials.
     * @return the role of the authenticated user.
     * @throws AuthenticationException thrown if the login credentials are invalid.
     */
    public String userValidate(LoginDetails providedDetails) throws AuthenticationException {
        String netID = providedDetails.getNetID();
        String password = providedDetails.getPassword();

        User userToBeValidated = repository.findByNetIdAndPassword(netID, password);

        if (userToBeValidated == null) {
            throw new AuthenticationException();
        }
        String userEmail = userToBeValidated.email;
        String domainEmailPart = userEmail.split("@")[1];
        String userRole = domainEmailPart.split(".")[0];

        return userRole;
    }
}
