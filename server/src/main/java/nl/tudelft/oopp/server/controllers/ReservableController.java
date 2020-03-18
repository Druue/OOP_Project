package nl.tudelft.oopp.server.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.RoomResponse;
import nl.tudelft.oopp.api.models.ServerResponseAlert;
import nl.tudelft.oopp.server.models.Reservable;
import nl.tudelft.oopp.server.models.Room;
import nl.tudelft.oopp.server.services.LoggerService;
import nl.tudelft.oopp.server.services.ReservableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reservables")
public class ReservableController {

    /**
     * Importing the methods from the service class.
     */
    @Autowired
    ReservableService reservableService;

    private Gson gson = new GsonBuilder().serializeNulls().create();

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
    public ServerResponseAlert addNewRoom(@RequestBody String request) {
        Room requestRoom = gson.fromJson(request, Room.class);
        reservableService.addRoom(requestRoom);
        return new ServerResponseAlert("Room added", "CONFIRMATION");
    }


}
