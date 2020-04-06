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

    public HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

    private Logger logger = LoggerFactory.getLogger(ReservableController.class);

    private static final String NOT_ADMIN =
        "Unauthorized request. The requesting user is not an administrator.";

    private static final String NO_USER_FOUND =
        "Authentication for user failed. No administrator with that name found.";

    /**
     * Importing the methods from the service class.
     */
    final ReservableService reservableService;
    final AuthorizationService authorizationService;


    /** Construct a new {@link ReservableController} bean using the following beans.
     * @param reservableService     The {@link ReservableService} bea to use when fetching, adding
     *                              and deleting reservables.
     * @param authorizationService  The {@link AuthorizationService} bean to use when authenticating
     *                              and authorizing users and administrators.
     */
    public ReservableController(ReservableService reservableService,
                                AuthorizationService authorizationService) {
        this.reservableService = reservableService;
        this.authorizationService = authorizationService;

    }

    /**
     * Receives a GET request for all rooms or bikes of a particular building. First uses the
     * {@link nl.tudelft.oopp.server.services.ReservableService} to fetch all rooms or bikes
     * of the building with the provided id as a request parameter. Then sends the list wrapped
     * in a {@link ResponseEntity} object.
     *
     * @param number The id of the building to fetch the rooms of.
     * @param type   The type of reservable to retrieve - rooms or bikes.
     * @return A {@link ResponseEntity} object containing a list of the building's rooms.
     */
    @GetMapping("/{role:(?:user|admin)}/all/{type}/building")
    public ResponseEntity<List<Reservable>> getAllReservablesOfBuilding(
            @RequestParam Long number,
            @PathVariable String type) {

        logger.info("Received GET requests for all " + type
                + " of building " + number + ". Processing ...");

        logger.info("Fetching all + " + type + " of building " + number + " ...");

        List<Reservable> reservablesToSend;

        try {
            reservablesToSend = reservableService.getAllReservablesForBuilding(number, type);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }

        logger.info("Sending the " + type + " of building " + number + " ...");
        return ResponseEntity.ok(reservablesToSend);
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
            logger.error(NO_USER_FOUND);
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
     * Deletes a specific reservable from the database.
     *
     * @param id the ID of the reservable to delete.
     */
    @DeleteMapping("/Reservable/{id}")
    public void delete(@PathVariable Long id) {
        reservableService.deleteReservable(id);
    }

}
