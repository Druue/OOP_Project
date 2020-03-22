package nl.tudelft.oopp.server.controllers;

import com.google.gson.Gson;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.UserAuthResponse;
import nl.tudelft.oopp.server.models.User;
import nl.tudelft.oopp.server.services.LoggerService;
import nl.tudelft.oopp.server.services.RegistrationService;
import nl.tudelft.oopp.server.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    public static final Gson gson = new Gson();
    final RegistrationService registrationService;
    final UserService userService;

    public RegistrationController(RegistrationService registrationService, UserService userService) {
        this.registrationService = registrationService;
        this.userService = userService;
    }

    /**
     * This method is the entry point for the registration procedure. It accepts as input the
     * provided by the user registration details encapsulated in a RegistrationDetails object, with
     * the password hashed in advance by the client. Check: The method tries to establish whether a
     * client with such a username already exists in the database and if so, sends a response
     * informing the user to provide another username If no user with the provided username exists,
     * the method adds the new user to the database and sends a response that informs the user he
     * has been successfully registered.
     */
    @PostMapping("/register")
    public ResponseEntity<UserAuthResponse> registerUser(@RequestBody User userRequest) {

        // Log the registration attempt
        LoggerService.info(RegistrationController.class, "Received registration details");
        LoggerService.info(RegistrationController.class, userRequest.email);

        //checks if a user already exists with this email.
        User test = userService.getUserByEmail(userRequest.email);
        if (test != null) {
            LoggerService.info(RegistrationController.class, "User already exists with this email");
            return ResponseEntity.badRequest().body(
                    new UserAuthResponse("User already exists with this email", "ERROR", null));
        }
        try {
            // Attempts to add the user to the database.
            registrationService.addUser(userRequest);

            // Converts the user into an API model User.
            // This has to be done via their email, as they don't know their ID during registration.
            nl.tudelft.oopp.api.models.User user = HttpRequestHandler.convertModel(
                    registrationService.getUserByEmail(userRequest.email),   //from
                    nl.tudelft.oopp.api.models.User.class);             //to

            // Return a UserAuthResponse, and log the registration.
            UserAuthResponse r =
                    new UserAuthResponse("You've registered successfully!", "CONFIRMATION", user);
            LoggerService.info(RegistrationController.class, "New user successfully registered.");
            return ResponseEntity.ok().body(r);

        } catch (Exception /* InstanceAlreadyExistsException */ e) {

            // Print the debugging info, and send a request back to the client
            // Stating that the user has made an invalid registration attempt.
            e.printStackTrace();
            LoggerService.error(RegistrationController.class,
                    "Invalid details provided. User with that "
                            + "username already exists in the database.");

            UserAuthResponse r = new UserAuthResponse("Invalid details provided!", "ERROR", null);
            return ResponseEntity.badRequest().body(r);
        }


    }

}
