package nl.tudelft.oopp.server.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.management.InstanceAlreadyExistsException;
import javax.naming.AuthenticationException;
import javax.persistence.EntityNotFoundException;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.BuildingResponse;
import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.api.models.ListPair;
import nl.tudelft.oopp.api.models.ServerResponseAlert;
import nl.tudelft.oopp.server.models.AuthorizationException;
import nl.tudelft.oopp.server.models.Building;
import nl.tudelft.oopp.server.models.Details;
import nl.tudelft.oopp.server.models.TimeSlot;
import nl.tudelft.oopp.server.repositories.BuildingsDetails;
import nl.tudelft.oopp.server.services.AuthorizationService;
import nl.tudelft.oopp.server.services.BuildingService;
import nl.tudelft.oopp.server.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/buildings")
public class BuildingRequestController {

    public HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

    private static final String NOT_ADMIN =
        "Unauthorized request. The requesting user is not an administrator.";
    private static final String NO_USER_FOUND =
        "Authentication for user failed. No administrator with that name found.";
    final BuildingService buildingService;
    final AuthorizationService authorizationService;
    final Logger logger = LoggerFactory.getLogger(BuildingRequestController.class);

    /**
     * Create a new {@link BuildingRequestController} using the following beans.
     *
     * @param buildingService      The {@link BuildingService} bean to use for building operations.
     * @param userService          The {@link UserService} bean to use for interactions with the users table.
     * @param authorizationService The {@link AuthorizationService} bean to use for user
     *                             validation.
     */
    public BuildingRequestController(BuildingService buildingService, UserService userService,
                                     AuthorizationService authorizationService) {
        this.buildingService = buildingService;
        this.authorizationService = authorizationService;
    }

    /**
     * Receives a GET request for information about all buildings in the database.
     * Sends back a list of objects, each containing:
     * - number, name, description, image, opening hours
     *
     * @return A {@link ResponseEntity} containing he aforementioned list of objects.
     */
    @GetMapping("/{name:(?:admin|user)}/all/information")
    ResponseEntity<List<BuildingsDetails>> sendAllBuildingsInformation() {

        logger.info("Received GET request for all buildings' details. Processing...");
        List<BuildingsDetails> buildings = buildingService.getBuildingsDetails();
        return ResponseEntity.ok(buildings);

    }

    /**
     * Receives e PUT request for adding a new building to the database.
     *
     * @param requestBuilding A {@link ClientRequest} object containing the new Building to insert.
     * @return A {@link ResponseEntity} object indicating the success of the operation.
     */
    @PutMapping(value = "/admin/add")
    ResponseEntity<ServerResponseAlert> addBuilding(
        @RequestBody ClientRequest<Building> requestBuilding) {

        logger.info("Received PUT request for adding a new building. Processing ...");

        try {
            authorizationService.checkAuthorization(requestBuilding.getUsername());
        } catch (AuthenticationException e) {
            logger.error(NO_USER_FOUND);
            return ResponseEntity.badRequest().build();
        } catch (AuthorizationException e) {
            logger.error(NOT_ADMIN);
            return ResponseEntity.badRequest().build();
        }

        try {
            buildingService.addBuilding(requestBuilding.getBody());
        } catch (InstanceAlreadyExistsException e) {
            logger.error("Failure to add the new building. Building number or details "
                + "name already exists!");
            return ResponseEntity.badRequest().build();
        }

        logger.info("Adding of new building done successfully");
        return ResponseEntity.ok(new ServerResponseAlert("Adding successful",
            "INFORMATION"));
    }

}
