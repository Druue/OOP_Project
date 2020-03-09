package nl.tudelft.oopp.server.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.RoomResponse;
import nl.tudelft.oopp.api.models.ServerResponseAlert;
import nl.tudelft.oopp.server.models.Reservable;
import nl.tudelft.oopp.server.models.Room;
import nl.tudelft.oopp.server.services.LoggerService;
import nl.tudelft.oopp.server.services.ReservableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("reservables")
public class ReservableController {

    /**
     * Importing the methods from the service class.
     */
    @Autowired
    ReservableService reservableService;

    private Gson gson = new GsonBuilder().serializeNulls().create();

    @GetMapping("/all/rooms")
    public ResponseEntity<RoomResponse> getAllReservables() {
        LoggerService.info(ReservationsController.class,
                "Received request for all reservables");


        List<nl.tudelft.oopp.api.models.Room> responseList = new ArrayList<>();
        for (Reservable responseReservable: reservableService.getAllReservables()) {
            if (responseReservable instanceof Room) {
                LoggerService.info(ReservableController.class, (HttpRequestHandler.convert(
                        responseReservable, nl.tudelft.oopp.api.models.Room.class
                ).getName()));
                responseList.add(HttpRequestHandler.convert(
                        responseReservable, nl.tudelft.oopp.api.models.Room.class
                ));
            }

        }
        return ResponseEntity.ok(new RoomResponse(responseList));
    }

    @PutMapping("/insert/new_room")
    public ServerResponseAlert addNewRoom(@RequestBody String request) {
        Room requestRoom = gson.fromJson(request, Room.class);
        reservableService.addRoom(requestRoom);
        return new ServerResponseAlert("Room added", "CONFIRMATION");
    }


}
