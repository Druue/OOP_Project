package nl.tudelft.oopp.server.controllers;

import com.google.gson.Gson;
import nl.tudelft.oopp.api.models.UserAuthResponse;
import nl.tudelft.oopp.server.models.User;
import nl.tudelft.oopp.server.services.LoggerService;
import nl.tudelft.oopp.server.services.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    public static final Gson gson = new Gson();
    final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
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
    public ResponseEntity<UserAuthResponse> registerUser(@RequestBody String jsonRequest) {
        LoggerService.info(RegistrationController.class, "Received registration details");

        User registrationRequest = gson.fromJson(jsonRequest, User.class);
        LoggerService.info(RegistrationController.class, registrationRequest.email);

        try {
            // TODO: Validate that the user doesn't already exist.
            registrationService.addUser(registrationRequest);
            nl.tudelft.oopp.api.models.User user = gson.fromJson(
                    gson.toJson(registrationService
                            .getUserByID(registrationService.getUserId(registrationRequest.email))),
                    nl.tudelft.oopp.api.models.User.class);
            UserAuthResponse r =
                    new UserAuthResponse("You've registered successfully!", "CONFIRMATION", user);
            LoggerService.info(RegistrationController.class, "New user successfully registered.");
            return ResponseEntity.ok().body(r);
        } catch (Exception /* InstanceAlreadyExistsException */ e) {
            e.printStackTrace();
            LoggerService.error(RegistrationController.class,
                    "Invalid details provided. User with that "
                            + "username already exists in the database.");

            UserAuthResponse r = new UserAuthResponse("Invalid details provided!", "ERROR", null);
            return ResponseEntity.badRequest().body(r);
        }


    }

}
