package nl.tudelft.oopp.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import nl.tudelft.oopp.server.models.Room;
import nl.tudelft.oopp.server.repositories.ReservableRepository;
import nl.tudelft.oopp.server.repositories.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoomService {

    Logger logger = LoggerFactory.getLogger(RoomService.class);
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**method loops through all rooms and checks which ones are for employees only and returns it.
     *
     * @return a list of all rooms
     */
    public List<Room> filterByIsForEmployee() {
        List<Room> roomsForEmployee = new ArrayList<>();
        List<Room> rooms = roomRepository.findAll();
        for (Room room : rooms) {
            if (room.forEmployee) {
                roomsForEmployee.add(room);
            }
        }
        return roomsForEmployee;
    }

    /**
     * method to filter all rooms by capacity.
     * @param userInput what a user selects
     * @return a list of rooms with capactiy <= user input.
     */
    public List<Room> filterByCapacity(int userInput) {
        List<Room> result = new ArrayList<>();
        List<Room> rooms = roomRepository.findAll();
        for (Room room : rooms) {
            if (room.capacity >= userInput) {
                result.add(room);
            }
        }
        return result;
    }

    /**method to return all rooms that has a projector.
     *
     * @return a list of rooms.
     */
    public List<Room> filterByHasProjector() {
        List<Room> result = new ArrayList<>();
        List<Room> rooms = roomRepository.findAll();
        for (Room room : rooms) {
            if (room.hasProjector) {
                result.add(room);
            }
        }
        return result;
    }
}
