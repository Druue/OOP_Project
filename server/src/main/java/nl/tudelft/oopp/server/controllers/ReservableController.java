package nl.tudelft.oopp.server.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.api.models.ReservableResponse;
import nl.tudelft.oopp.api.models.ServerResponseAlert;
import nl.tudelft.oopp.server.models.Reservable;
import nl.tudelft.oopp.server.models.Room;
import nl.tudelft.oopp.server.services.LoggerService;
import nl.tudelft.oopp.server.services.ReservableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/reservables")
public class ReservableController {

    /**
     * Importing the methods from the service class.
     */
    @Autowired
    ReservableService reservableService;

    private Gson gson = new GsonBuilder().serializeNulls().create();

    @GetMapping("/all")
    public ResponseEntity<ReservableResponse> getAllReservables() {
        LoggerService.info(ReservationsController.class,
                "Received request for all reservables");


        List<nl.tudelft.oopp.api.models.Reservable> responseList = new ArrayList<>();
        for (Reservable responseReservable: reservableService.getAllReservables()) {
            if (responseReservable instanceof Room) {
                responseList.add(gson.fromJson(gson.toJson(responseReservable), nl.tudelft.oopp.api.models.Room.class));
            }

        }
        return ResponseEntity.ok(new ReservableResponse(responseList));
    }

    @PutMapping("/insert/new_room")
    public ServerResponseAlert addNewRoom(@RequestBody String request) {
        Room requestRoom = gson.fromJson(request, Room.class);

        System.out.println(request);
        Room room = new Room(request, true, false);
        reservableService.addRoom(room);
        return new ServerResponseAlert("Room added", "CONFIRMATION");
    }


}
