package nl.tudelft.oopp.server.controllers;

import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.Building;
import nl.tudelft.oopp.api.models.BuildingResponse;
import nl.tudelft.oopp.api.models.ServerResponseAlert;
import nl.tudelft.oopp.server.services.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buildings")
public class BuildingRequestController {

    @Autowired
    BuildingService service;

    @GetMapping("/all")
    ResponseEntity<BuildingResponse> sendAllBuildings() {

        // LoggerService.info(BuildingRequestController.class,
        // "Request for all available buildings received. Processing ...");
        // System.out.println(session);
        // try {
        // if (session.getAttribute("username") == null) {
        // throw new IllegalAccessException();
        // }
        // } catch (IllegalAccessException exception) {
        // LoggerService.error(BuildingRequestController.class,
        // "Unauthorized attempt to view all buildings. No session found for this user.");
        // return ResponseEntity.badRequest().build();
        // }


        List<Building> buildings = new ArrayList<>();
        for (nl.tudelft.oopp.server.models.Building queryBuilding : service.getAllBuildings()) {
            buildings.add(HttpRequestHandler.convertModel(queryBuilding, Building.class));
        }

        BuildingResponse response = new BuildingResponse(buildings);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/availableOnDay")
    ResponseEntity<List<Building>> sendAvailableBuildingsForDay(/* TODO */) {
        // TODO
        return null;
    }

    @PutMapping("/insert/new_building")
    ResponseEntity<ServerResponseAlert> addBuilding(@RequestBody nl.tudelft.oopp.server.models.Building requestBuilding) {
        service.addBuilding(requestBuilding);
        return ResponseEntity.ok(new ServerResponseAlert("Building added!", "CONFIRMATION"));
    }

    @PutMapping("/insert/foodcourt")
    ResponseEntity<ServerResponseAlert> addBuildingFoodCourt(/* TODO */) {
        // TODO
        return null;
    }

    @PostMapping("/changeAvailability/closeForDay")
    public ResponseEntity<ServerResponseAlert> closeBuildingForDay(/* TODO */) {
        // TODO
        return null;
    }

    @PostMapping("changeAvailability/openForDay")
    public ResponseEntity<ServerResponseAlert> openBuildingForDay(/* TODO */) {
        // TODO
        return null;
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<ServerResponseAlert> deleteBuilding(/* TODO */) {
        // TODO
        return null;
    }
}
