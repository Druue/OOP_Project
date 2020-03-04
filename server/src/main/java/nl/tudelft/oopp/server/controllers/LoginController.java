package nl.tudelft.oopp.server.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import javax.naming.AuthenticationException;
import nl.tudelft.oopp.api.models.Building;
import nl.tudelft.oopp.api.models.BuildingResponse;
import nl.tudelft.oopp.api.models.LoginRequest;
import nl.tudelft.oopp.api.models.ServerResponse;
import nl.tudelft.oopp.server.services.LoggerService;
import nl.tudelft.oopp.server.services.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// This class contains the method to answer to a login request from the client
// It receives as input the provided by the user NetID and a hashed password.
@RestController
public class LoginController {

    private final LoginService service;

    @Autowired
    public LoginController(LoginService service) {
        this.service = service;
    }

    /**
     * This method logs in a user to the application with provided
     * NetID and password. It calls the method userValidate() of the LoginService class to
     * query the database for the user.
     * @param request - The request provided by the user details mapped to a LoginDetails object
     *                        through the @RequestBody annotation.
     * @return An instance of ResponseEntity with status code 200 if the user is successfully authenticated.
     *              Otherwise returns Bad Request response.
     */
    @PostMapping(value = "login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ServerResponse> validateAuthentication(@RequestBody JsonObject request) {

        Gson gson = new Gson();
        LoginRequest loginRequest = gson.fromJson(request, LoginRequest.class);
        try {
            String role = service.userValidate(loginRequest);
            LoggerService.info(LoginController.class, "User successfully authenticated.");

            // Send a response containing a success message, and the user's role.
            ServerResponse a = new ServerResponse("Successful login!", "CONFIRMATION");
            return ResponseEntity.ok().body(a);
        } catch (AuthenticationException e) {
            LoggerService.info(LoginController.class, "Authentication failed for user with NetID: "
                                                      + loginRequest.getNetID() + " and password "
                                                      + loginRequest.getPassword()
                                                      + " : No such user registered.");

            // Send a response containing an error message.
            ServerResponse a = new ServerResponse("Invalid user/password combination.", "ERROR");
            return ResponseEntity.badRequest().body(a);
        }
    }

    /**
     * An example mapping to showcase the use of the new, refactored API.
     * @return A response entity, with a {@link BuildingResponse} body.
     */
    @GetMapping(value = "getbuildings", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BuildingResponse> getBuildings() {

        // Make some example buildings, and add them to a BuildingResponse object.
        Building b = new Building("b",1, 500);
        Building a = new Building("a", 2, 1000);
        Building c = new Building("c", 3, 1500);
        List<Building> buildingList = new ArrayList<>();
        buildingList.add(a);
        buildingList.add(b);
        buildingList.add(c);
        BuildingResponse response = new BuildingResponse(buildingList);

        return ResponseEntity.ok().body(response);
    }
}
