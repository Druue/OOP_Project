package nl.tudelft.oopp.demo.controllers;


import nl.tudelft.oopp.demo.models.RegistrationDetails;
import nl.tudelft.oopp.demo.services.LoggerService;
import nl.tudelft.oopp.demo.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.InstanceAlreadyExistsException;
import java.util.logging.Logger;

@RestController
public class RegistrationController {

    private final RegistrationService service;

    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    /* This method is the entry point for the registration procedure. It accepts as input
       the provided by the user registration details encapsulated in a RegistrationDetails
       object, with the password hashed in advance by the client.
       Check: The method tries to establish whether a client with such a NetID already exists
       in the database and if so, sends a response informing the user to provide another NetID
       If no user with the provided NetID exists, the method adds the new user to the database
       and sends a response that informs the user he has been successfully registered.
    * */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationDetails registrationDetails) {

        LoggerService.info(RegistrationController.class , "Received registration details");

        try {
            service.registerUser(registrationDetails);
        }

        catch (Exception /* InstanceAlreadyExistsException */ e) {
            LoggerService.error(RegistrationController.class , "Invalid details provided. User with that " +
                    "NetID already exists in the database.");
            return ResponseEntity.badRequest().header("Reason" , "User already exists")
                    .build();
        }

        LoggerService.info(RegistrationController.class , "New user successfully registered.");
        return ResponseEntity.ok().build();
    }

}
