package nl.tudelft.oopp.demo.services;

import javax.management.InstanceAlreadyExistsException;
import nl.tudelft.oopp.demo.models.RegistrationDetails;
import nl.tudelft.oopp.demo.models.User;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class RegistrationService {

    final UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * This method registers a new user in the database of the application. If the user already
     * exists, throws exception.
     *
     * @param registrationDetails The user provided registration details
     * @throws InstanceAlreadyExistsException - throws it if a user with the provided NetID already
     *                                        exists
     */
    public void registerUser(RegistrationDetails registrationDetails)
        throws InstanceAlreadyExistsException {

        User user = new User(registrationDetails);
        if (userRepository.existsByNetId(user.netId)) {
            throw new InstanceAlreadyExistsException();
        } else if (userRepository.existsByEmail(user.email)) {
            throw new InstanceAlreadyExistsException();
        } else {
            userRepository.save(user);
        }
    }

}
