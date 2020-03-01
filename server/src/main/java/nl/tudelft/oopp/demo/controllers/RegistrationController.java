package nl.tudelft.oopp.demo.controllers;


import nl.tudelft.oopp.demo.models.RegistrationDetails;
import nl.tudelft.oopp.demo.models.RegistrationResponse;
import nl.tudelft.oopp.demo.services.LoggerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.InstanceAlreadyExistsException;
import java.util.logging.Logger;

@RestController
public class RegistrationController {

    /* This method is the entry point for the registration procedure. It accepts as input
       the provided by the user registration details encapsulated in a RegistrationDetails
       object, with the password hashed in advance by the client.
       Check: The method tries to establish whether a client with such a NetID already exists
       in the database and if so, sends a response informing the user to provide another NetID
       If no user with the provided NetID exists, the method adds the new user to the database
       and sends a response that informs the user he has been successfully registered.
    * */
    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody RegistrationDetails registrationDetails) {

        LoggerService.info(RegistrationController.class , "Received registration details");

        try {
            /*
               Use a method to search the database for the NetID. If found,
               the method will throw InstanceAlreadyExistsException() for catching.
               If not, use another method to insert the new user.
            * */

        } catch (Exception /* InstanceAlreadyExistsException */ e) {
            LoggerService.error(RegistrationController.class, "Invalid details provided. User with that "
                                                               + "NetID already exists in the database.");

            RegistrationResponse r = new RegistrationResponse("Invalid details provided!", "ERROR");
            return ResponseEntity.badRequest().body(r);
        }

        LoggerService.info(RegistrationController.class, "New user successfully registered.");
        RegistrationResponse r = new RegistrationResponse("You've registered successfully!","CONFIRMATION");
        return ResponseEntity.ok().body(r);
    }

}
