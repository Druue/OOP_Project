package nl.tudelft.oopp.server.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import nl.tudelft.oopp.api.models.RegistrationRequest;
import nl.tudelft.oopp.api.models.ServerResponseAlert;
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
     * client with such a NetID already exists in the database and if so, sends a response informing
     * the user to provide another NetID If no user with the provided NetID exists, the method adds
     * the new user to the database and sends a response that informs the user he has been
     * successfully registered.
     */
    @PostMapping("/register")
    public ResponseEntity<ServerResponseAlert> registerUser(@RequestBody String jsonRequest) {
        LoggerService.info(RegistrationController.class, "Received registration details");

        RegistrationRequest registrationRequest = gson.fromJson(jsonRequest, RegistrationRequest.class);
        try {
            registrationService.registerUser(registrationRequest);
        } catch (Exception /* InstanceAlreadyExistsException */ e) {
            LoggerService.error(RegistrationController.class,
                    "Invalid details provided. User with that "
                            + "NetID already exists in the database.");

            ServerResponseAlert r = new ServerResponseAlert("Invalid details provided!", "ERROR");
            return ResponseEntity.badRequest().body(r);
        }

        LoggerService.info(RegistrationController.class, "New user successfully registered.");
        ServerResponseAlert r = new ServerResponseAlert("You've registered successfully!", "CONFIRMATION");
        return ResponseEntity.ok().body(r);
    }

}
