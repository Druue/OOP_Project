package nl.tudelft.oopp.server.controllers;

import java.util.List;
import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.api.models.ServerResponseAlert;
import nl.tudelft.oopp.server.models.Building;
import nl.tudelft.oopp.server.repositories.BuildingsDetails;
import nl.tudelft.oopp.server.services.BuildingService;
import nl.tudelft.oopp.server.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buildings")
public class BuildingRequestController {

    final BuildingService buildingService;
    final UserService userService;
    final Logger logger = LoggerFactory.getLogger(BuildingRequestController.class);

    public BuildingRequestController(BuildingService buildingService, UserService userService) {
        this.buildingService = buildingService;
        this.userService = userService;
    }

    /** Receives a GET request for information about all buildings in the database.
     *      Sends back a list of objects, each containing:
     *      - number, name, description, image, opening hours
     * @return A {@link ResponseEntity} containing he aforementioned list of objects.
     */
    @GetMapping("/{name:(?:admin|user)}/all")
    ResponseEntity<List<BuildingsDetails>> sendAllBuildings() {

        logger.info("Received GET request for all buildings' details. Processing...");
        List<BuildingsDetails> buildings = buildingService.getBuildingsDetails();
        return ResponseEntity.ok(buildings);

    }

    /**
     * Receives e PUT request for adding a new building to the database.
     *
     * @param request A {@link ClientRequest} object containing the new Building to insert.
     * @return A {@link ResponseEntity} object indicating the success of the operation.
     */
    @PutMapping("/admin/add")
    ResponseEntity<ServerResponseAlert> addBuilding(
        @RequestBody ClientRequest<Building> request) {

        logger.info("Received PUT request for adding a building. Processing...");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/change/name")
    public ResponseEntity<ServerResponseAlert> adminChangeBuildingDetails(
        @RequestBody ClientRequest<Building> request) {
        // TODO
        return ResponseEntity.ok().build();
    }

    /**
     * Receives a DELETE request for deleting a specific building from the database.
     *
     * @param request The {@link ClientRequest} object containg the building to delete.
     * @return A {@link ResponseEntity} object indicating the success of the operation.
     */
    @DeleteMapping("/admin/delete")
    ResponseEntity<ServerResponseAlert> deleteBuilding(
        @RequestBody ClientRequest<Building> request) {

        return ResponseEntity.ok().build();
    }
}
