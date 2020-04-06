package nl.tudelft.oopp.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import javax.management.InstanceAlreadyExistsException;
import javax.naming.AuthenticationException;
import javax.persistence.EntityNotFoundException;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.api.models.ReservableResponse;
import nl.tudelft.oopp.api.models.RoomResponse;
import nl.tudelft.oopp.api.models.ServerResponseAlert;
import nl.tudelft.oopp.server.models.Reservable;
import nl.tudelft.oopp.server.models.Room;
import nl.tudelft.oopp.server.services.AuthorizationService;
import nl.tudelft.oopp.server.services.LoggerService;
import nl.tudelft.oopp.server.services.ReservableService;
import nl.tudelft.oopp.server.services.RoomFilteringService;
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

    public HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

    private Logger logger = LoggerFactory.getLogger(ReservableController.class);

    /**
     * Importing the methods from the service class.
     */
    final ReservableService reservableService;
    final AuthorizationService authorizationService;
    final RoomFilteringService roomService;

    /** Construct a new {@link ReservableController} bean using the following beans.
     * @param reservableService     The {@link ReservableService} bea to use when fetching, adding
     *                              and deleting reservables.
     * @param authorizationService  The {@link AuthorizationService} bean to use when authenticating
     *                              and authorizing users and administrators.
     * @param roomService           The {@link RoomFilteringService} bean to use when filtering rooms.
     */
    public ReservableController(ReservableService reservableService,
                                AuthorizationService authorizationService,
                                RoomFilteringService roomService) {
        this.reservableService = reservableService;
        this.authorizationService = authorizationService;
        this.roomService = roomService;
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
                try {
                    LoggerService.info(ReservableController.class, (httpRequestHandler.convertModel(
                        responseReservable, nl.tudelft.oopp.api.models.Room.class
                    ).getDetails().getName()));
                } catch (NullPointerException npe) {
                    LoggerService.info(ReservableController.class, "Name of room is null");
                }
                responseList.add(httpRequestHandler.convertModel(
                        responseReservable, nl.tudelft.oopp.api.models.Room.class
                ));
            }

        }
        return ResponseEntity.ok(new RoomResponse(responseList));
    }

    /** Receives a GET request for all rooms or bikes of a particular building. First uses the
     *      {@link nl.tudelft.oopp.server.services.ReservableService} to fetch all rooms or bikes
     *      of the building with the provided id as a request parameter. Then sends the list wrapped
     *      in a {@link ResponseEntity} object.
     *
     * @param number        The id of the building to fetch the rooms of.
     * @param type      The type of reservable to retrieve - rooms or bikes.
     * @return          A {@link ResponseEntity} object containing a list of the building's rooms.
     */
    @GetMapping("/{role:(?:user|admin)}/all/{type}/building/{number}")
    public ResponseEntity<ReservableResponse> getAllReservablesOfBuilding(
        @PathVariable Long number,
        @PathVariable String type) {

        logger.info("Received GET requests for all " + type
            + " of building " + number + ". Processing ...");

        logger.info("Fetching all + " + type + " of building " + number + " ...");

        List<Reservable> reservables;

        try {
            reservables = reservableService.getAllReservablesForBuilding(number, type);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }

        List<nl.tudelft.oopp.api.models.Reservable> reservablesToSend = new ArrayList<>();

        for (Reservable reservable:reservables) {
            reservablesToSend.add(httpRequestHandler.convertModel(reservable, nl.tudelft.oopp.api.models.Reservable.class));
        }

        logger.info("Sending the " + type + " of building " + number +  " ...");
        return ResponseEntity.ok(new ReservableResponse(reservablesToSend));
    }

    /** Receives a GET request for all rooms filtered by a certain capacity provided as a request
     *      parameter. First fetches all rooms from the database with the required capacity using
     *      the {@link RoomFilteringService} bean, and then sends the fetched list wrapped in a
     *      {@link ResponseEntity} object.
     * @param group     The partition of rooms with respect to the provided capacity.
     * @param capacity  The provided by the user capacity for filtering.
     * @return          A {@link ResponseEntity} containing a list of {@link Room} objects.
     */
    @GetMapping("/{role:(?:user|admin)}/filter/capacity/{group}")
    public ResponseEntity<List<Room>> getAllRoomsByFilterCapacity(
        @PathVariable String group,
        @RequestParam Integer capacity) {

        logger.info("Received GET request for all available rooms "
                + "with capacity " + group + " than " + capacity);

        List<Room> reservablesToBeSend = roomService.findAllByCapacity(group, capacity);

        logger.info("Sending the filtered by capacity list ...");
        return ResponseEntity.ok(reservablesToBeSend);

    }

    /** Endpoint that receives a PUT request for adding a new reservable to a building whose
     *      id is provided as a request parameter and the type of reservable as a path variable.
     *      The new reservable is encapsulated in a {@link ClientRequest} object.
     * @param request   The {@link ClientRequest} object containing the new reservable to add.
     * @param id        The id of the building to add the new reservable to.
     * @param type      The type of reservable to add - room or bike.
     * @return          A {@link ResponseEntity} object indicating whether the operation was
     *                  successful.
     */
    @PutMapping("/insert/{type}/{id}")
    public ResponseEntity<ServerResponseAlert> addNewReservable(
        @RequestBody ClientRequest<String> request,
        @PathVariable Long id,
        @PathVariable String type) {

        logger.info("Received PUT request for adding a new " + type + " to building " + id
            + ". Processing ...");

        try {
            authorizationService.authenticateUser(request.getUsername());
        } catch (AuthenticationException e) {
            logger.error(AuthorizationService.NO_USER_FOUND);
            return ResponseEntity.badRequest().build();
        }

        logger.info("Adding new " + type + " to building " + id);

        try {
            Reservable reservableToAdd = new ObjectMapper().readValue(
                request.getBody(),
                Reservable.class);
            reservableService.addReservable(reservableToAdd, id);

        } catch (EntityNotFoundException e) {
            logger.error("Building " + id + " not found!");
            return ResponseEntity.badRequest().body(new ServerResponseAlert("Building not found with this number", "ERROR"));
        } catch (InstanceAlreadyExistsException | JsonProcessingException e) {
            return ResponseEntity.badRequest().body(new ServerResponseAlert(
               "Reservable with that name already exists",
                "ERROR"
            ));
        }

        logger.info("Adding of " + type + " to building " + id + " successful. ");
        return ResponseEntity.ok(new ServerResponseAlert("Adding of " + type
            + "successful.", "SUCCESS"));
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

}
