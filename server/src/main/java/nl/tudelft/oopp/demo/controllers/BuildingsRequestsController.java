package nl.tudelft.oopp.demo.controllers;

import java.util.List;
import javax.servlet.http.HttpSession;
import nl.tudelft.oopp.demo.models.Building;
import nl.tudelft.oopp.demo.models.ServerResponse;
import nl.tudelft.oopp.demo.services.BuildingService;
import nl.tudelft.oopp.demo.services.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buildings")
public class BuildingsRequestsController {

    @Autowired
    BuildingService service;

    @GetMapping("/all")
    ResponseEntity<List<Building>> sendAllBuildings(HttpSession session) {

        LoggerService.info(BuildingsRequestsController.class,
            "Request for all available buildings received. Processing ...");
        System.out.println(session);
        try {
            if (session.getAttribute("NetID") == null) {
                throw new IllegalAccessException();
            }
        } catch (IllegalAccessException exception) {
            LoggerService.error(BuildingsRequestsController.class,
                "Unauthorized attempt to view all buildings. No session found for this user.");
            return ResponseEntity.badRequest().build();
        }

        List<Building> buildings = service.getAllBuildings();
        return ResponseEntity.ok().body(buildings);
    }

    @GetMapping("/availableOnDay")
    ResponseEntity<List<Building>> sendAvailableBuildingsForDay(/*TODO*/) {
        // TODO
        return null;
    }

    @PutMapping("/insert/new_building")
    ResponseEntity<ServerResponse> addBuilding(/*TODO*/) {
        //TODO
        return null;
    }

    @PutMapping("insert/foodcourt")
    ResponseEntity<ServerResponse> addBuildingFoodCourt(/*TODO*/) {
        // TODO
        return null;
    }

    @PostMapping("changeAvailability/closeForDay")
    public ResponseEntity<ServerResponse> closeBuildingForDay(/*TODO*/) {
        // TODO
        return null;
    }

    @PostMapping("changeAvailability/openForDay")
    public ResponseEntity<ServerResponse> openBuildingForDay(/*TODO*/) {
        // TODO
        return null;
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<ServerResponse> deleteBuilding(/*TODO*/) {
        // TODO
        return null;
    }



}
