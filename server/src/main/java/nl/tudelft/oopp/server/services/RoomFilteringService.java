package nl.tudelft.oopp.server.services;

import java.util.List;
import nl.tudelft.oopp.server.models.Reservable;
import nl.tudelft.oopp.server.models.Room;
import nl.tudelft.oopp.server.repositories.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class RoomFilteringService {

    private final RoomRepository roomRepository;
    Logger logger = LoggerFactory.getLogger(RoomFilteringService.class);


    public RoomFilteringService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    /**
     * This method uses the {@link RoomRepository} bean to fetch all rooms that are only for
     * employees. It uses the
     *
     * @return a list of all rooms that are only for employees
     */
    public List<Room> findAllByIsForEmployee() {
        logger.info("Fetching all rooms for employees only ...");
        return roomRepository.getAllByForEmployeeTrue();
    }

    /**
     * method to filter all rooms by capacity.
     *
     * @param group     The partition of reservables regarding the provided capacity
     *                  - can be greater or smaller.
     * @param userInput The capacity to filter by.
     * @return a list of rooms with capactiy <= user input if group is "smaller" or a list of rooms
     *      with capacity >= user input if group is "greater".
     */
    public List<Room> findAllByCapacity(String group, int userInput) {

        logger.info("Beginning filtering by capacity ...");
        if (group.equals("greater")) {
            return roomRepository.getAllByCapacityGreaterThanEqual(userInput);
        } else {
            return roomRepository.getAllByCapacityLessThanEqual(userInput);
        }
    }

    public List<Room> findAllByHasProjector() {
        logger.info("Fetching all rooms with a projector ...");
        return roomRepository.getAllByHasProjectorTrue();
    }

}
