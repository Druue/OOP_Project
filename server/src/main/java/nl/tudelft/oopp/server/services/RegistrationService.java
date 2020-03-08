package nl.tudelft.oopp.server.services;

import javax.management.InstanceAlreadyExistsException;
import nl.tudelft.oopp.api.models.RegistrationRequest;
import org.springframework.stereotype.Service;



@Service
public class RegistrationService {

    /**
     * This method registers a new user in the database of the application.
     * If the user already exists, throws exception.
     * @param registrationRequest The user provided registration details
     * @throws InstanceAlreadyExistsException - throws it if a user with the provided NetID already exists
     */
    public void registerUser(RegistrationRequest registrationRequest) throws InstanceAlreadyExistsException {

    }

}
