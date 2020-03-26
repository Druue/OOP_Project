package nl.tudelft.oopp.server.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.naming.AuthenticationException;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.api.models.RoomResponse;
import nl.tudelft.oopp.api.models.ServerResponseAlert;
import nl.tudelft.oopp.server.models.Reservable;
import nl.tudelft.oopp.server.models.Room;
import nl.tudelft.oopp.server.services.AuthorizationService;
import nl.tudelft.oopp.server.services.LoggerService;
import nl.tudelft.oopp.server.services.ReservableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reservables")
public class ReservableController {

    private Logger logger = LoggerFactory.getLogger(ReservableController.class);
    private Gson gson = new GsonBuilder().serializeNulls().create();

    private static final String NOT_ADMIN =
        "Unauthorized request. The requesting user is not an administrator.";

    private static final String NO_USER_FOUND =
        "Authentication for user failed. No administrator with that name found.";

    /**
     * Importing the methods from the service class.
     */
    final ReservableService reservableService;
    final AuthorizationService authorizationService;

    public ReservableController(ReservableService reservableService,
                                AuthorizationService authorizationService) {
        this.reservableService = reservableService;
        this.authorizationService = authorizationService;
    }

    /**
     * Gets all rooms from the database.
     * @return A {@link RoomResponse} containing a list of all rooms.
     */
    @GetMapping("/all/rooms")
    public ResponseEntity<RoomResponse> getAllReservables() {
        LoggerService.info(ReservationsController.class,
                "Received request for all reservables");


        List<nl.tudelft.oopp.api.models.Room> responseList = new ArrayList<>();
        for (Reservable responseReservable: reservableService.getAllReservables()) {
            if (responseReservable instanceof Room) {
                LoggerService.info(ReservableController.class, (HttpRequestHandler.convertModel(
                        responseReservable, nl.tudelft.oopp.api.models.Room.class
                ).details.name));
                responseList.add(HttpRequestHandler.convertModel(
                        responseReservable, nl.tudelft.oopp.api.models.Room.class
                ));
            }

        }
        return ResponseEntity.ok(new RoomResponse(responseList));
    }

    /** Receives a GET request for all rooms of a particular building. First authenticates
     *      the user by his username using the {@link AuthorizationService} bean and then uses the
     *      {@link nl.tudelft.oopp.server.services.RoomService} to fetch all rooms of the building
     *      with the provided id as a request parameter. Sends the list of rooms wrapped in a
     *      {@link ResponseEntity} object.
     *
     * @param request The client request containing the username to be authenticated.
     * @param id The id of the building to fetch the rooms of.
     * @return A {@link ResponseEntity} object containing a list of the building's rooms.
     */
    @GetMapping("/user/all/rooms/building")
    public ResponseEntity<RoomResponse> getAllRoomsOfBuilding(
        @RequestBody ClientRequest<String> request,
        @RequestParam Long id) {

        logger.info("Received GET requests for all rooms of building " + id + ". Processing ...");

        try {
            authorizationService.authenticateUser(request.getUsername());
        } catch (AuthenticationException e) {
            logger.error(NO_USER_FOUND);
            return ResponseEntity.badRequest().build();
        }

        logger.info("Fetching all rooms of building " + id + " ...");
        // TODO

        logger.info("Sending the rooms of building " + id +  " ...");
        return null;
    }

    /**
     * Updates a room in the database.
     * @param newReservable the Reservable to replace the old reservable with.
     * @param id The id of the reservable that should be updated.
     */
    @PutMapping("models/Reservable/{id}")
    public void update(@RequestBody Reservable newReservable, @PathVariable Long id) {
        reservableService.updateReservable(id, newReservable);
    }
    
    /**
     * Deletes a specific reservable from the database.
     *
     * @param id the ID of the reservable to delete.
     */
    @DeleteMapping("/Reservable/{id}")
    public void delete(@PathVariable Long id) {
        reservableService.deleteReservable(id);
    }

    /**
     * Inserts a new room into the DB.
     * @param request The room that should be added.
     * @return A {@link ServerResponseAlert}.
     */    
    @PutMapping("/insert/new_room")
    public ServerResponseAlert addNewRoom(@RequestBody ClientRequest<Room> request) {
        //TODO: Add authorization process


        reservableService.addRoom(request.getBody());
        return new ServerResponseAlert("Room added", "CONFIRMATION");
    }


}
