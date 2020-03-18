package nl.tudelft.oopp.server.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.api.models.ServerResponseAlert;
import nl.tudelft.oopp.server.models.Building;
import nl.tudelft.oopp.server.services.BuildingService;
import nl.tudelft.oopp.server.services.UserService;
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

    final
    UserService userService;

    public BuildingRequestController(BuildingService buildingService, UserService userService) {
        this.buildingService = buildingService;
        this.userService = userService;
    }

    /**
     * Receives requests for all buildings in the database and sends
     * // TODO
     *
     * @return A {@link ResponseEntity} containing a list of the current buildings
     * with all the necessary info about them, including:
     * - id , name , image, description etc.
     */
    @GetMapping("/{name:(?:admin|user)}/all")
    ResponseEntity<List<Building>> sendAllBuildings() {

        List<Building> buildings = buildingService.getAllBuildings();
        return ResponseEntity.ok(buildings);

    }

    /**
     * Receives a GET request for the buildings open on a day.
     *
     * @param request The {@link ClientRequest} containing the {@link LocalDate} object
     *                representing the date for which to find the open buildings.
     * @return A {@link ResponseEntity} containing a list of buildings open on the day.
     */
    @GetMapping("/{name:(?:admin|user)}/day/special")
    ResponseEntity<List<Building>> sendAvailableBuildingsForSpecificDay(
        @RequestBody ClientRequest<LocalDate> request) {

        //TODO
        return ResponseEntity.ok().build();
    }

    /**
     * Receives a GET request for buildings open on a particular weekday.
     *
     * @param request The {@link ClientRequest} object containing the name of the weekday.
     * @return A {@link ResponseEntity} object containing a list of buildings
     * open on the weekday.
     */
    @GetMapping("/{name:(?:admin|user)}/day/week")
    ResponseEntity<List<Building>> sendAvailableBuildingsForWeekDay(
        @RequestBody ClientRequest<String> request) {

        //TODO
        return ResponseEntity.ok().build();
    }

    /**
     * Receives a GET request for the open weekdays of a building or all the buildings,
     * finds those days using the {@link BuildingService} bean and sends them back.
     *
     * @param request The {@link ClientRequest} containing the building for which to
     *                find the weekly open days, or null for getting the weekly open
     * @return A {@link ResponseEntity} object containing a list of the days of the week
     * the building is open on.
     */
    @GetMapping("/{name:(?:admin|user)}/opendays/week")
    ResponseEntity<Map<String, List<String>>> sendBuildingWeekOpenDays(
        @RequestBody ClientRequest<Building> request) {

        Map<String, List<String>> map = new HashMap<>();

        Building building = request.getBody();

        List<String> list = new ArrayList<>();
        list.add("Monday");
        list.add("Saturday");
        map.put("Ewi", list);

        return ResponseEntity.ok(map);
    }

    /**
     * Receives a GET request for holidays of a particular building
     * and sends them.
     *
     * @param request The {@link ClientRequest} object containing the specific building for
     *                which to send the holidays or null, in which case it sends the holidays
     *                of all buildings
     * @return A {@link ResponseEntity} object containing buildings' names and their
     * corresponding holidays, represented as Map object.
     */
    @GetMapping("/{name:(?:admin|user)}/closedays")
    ResponseEntity<Map<String, List<LocalDate>>> sendBuildingHolidays(
        @RequestBody ClientRequest<Building> request) {
        // TODO
        return ResponseEntity.ok().build();
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
        // TODO
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/change/name")
    public ResponseEntity<ServerResponseAlert> adminChangeBuildingName(
        @RequestBody ClientRequest<Building> request) {
        // TODO
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/change/image")
    public ResponseEntity<ServerResponseAlert> adminChangeBuildingImage(
        @RequestBody ClientRequest<Building> request) {

        return ResponseEntity.ok().build();
    }

    @PostMapping("admin/change/availability/weekday/close")
    public ResponseEntity<ServerResponseAlert> adminCloseBuildingOnWeekDay(/*TODO*/) {
        // TODO
        return ResponseEntity.ok().build();
    }

    @PostMapping("admin/change/availability/weekday/open")
    public ResponseEntity<ServerResponseAlert> adminOpenBuildingOnWeekDay(/*TODO*/) {
        // TODO
        return ResponseEntity.ok().build();
    }

    @PostMapping("admin/change/availability/specialday/close")
    public ResponseEntity<ServerResponseAlert> adminCloseOnSpecialDate(/*TODO*/) {
        // TODO
        return ResponseEntity.ok().build();
    }

    @PostMapping("admin/change/availability/specialday/open")
    public ResponseEntity<ServerResponseAlert> adminOpenOnSpecialDate(/*TODO*/) {
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
