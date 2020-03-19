package nl.tudelft.oopp.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import nl.tudelft.oopp.server.models.Room;
import nl.tudelft.oopp.server.repositories.ReservableRepository;
import nl.tudelft.oopp.server.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    /**method loops through all rooms and checks which ones are for employees only and returns it.
     *
     * @return a list of all rooms
     */
    public List<Room> filterByIsForEmployee() {
        List<Room> roomsForEmployee = new ArrayList<>();
        List<Room> rooms = roomRepository.findAll();
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).forEmployee) {
                roomsForEmployee.add(rooms.get(i));
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
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).capacity >= userInput) {
                result.add(rooms.get(i));
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
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).hasProjector) {
                result.add(rooms.get(i));
            }
        }
        return result;
    }
}
