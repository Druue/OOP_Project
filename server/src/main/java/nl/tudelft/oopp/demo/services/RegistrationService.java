package nl.tudelft.oopp.demo.services;

import nl.tudelft.oopp.demo.models.RegistrationDetails;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;

@Service
public class RegistrationService {

    /**
     * This method registers a new user in the database of the application.
     * If the user already exists, throws exception.
     * @param registrationDetails The user provided registration details
     * @throws InstanceAlreadyExistsException - throws it if a user with the provided NetID already exists
     */
    public void registerUser(RegistrationDetails registrationDetails) throws InstanceAlreadyExistsException {


    }

}
