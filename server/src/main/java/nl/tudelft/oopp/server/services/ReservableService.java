package nl.tudelft.oopp.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import nl.tudelft.oopp.server.models.Bike;
import nl.tudelft.oopp.server.models.Building;
import nl.tudelft.oopp.server.models.Reservable;
import nl.tudelft.oopp.server.models.Room;
import nl.tudelft.oopp.server.models.TimeSlot;
import nl.tudelft.oopp.server.repositories.BuildingRepository;
import nl.tudelft.oopp.server.repositories.ReservableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class ReservableService {

    private final ReservableRepository reservableRepository;
    private final BuildingService buildingService;

    Logger logger = LoggerFactory.getLogger(ReservableService.class);

    public ReservableService(ReservableRepository reservableRepository,
                             BuildingRepository buildingRepository,
                             BuildingService buildingService) {
        this.reservableRepository = reservableRepository;
        this.buildingService = buildingService;
    }

    /**
     * Gets a list of all reservables.
     *
     * @return a list of all reservables
     */
    public List<Reservable> getAllReservables() {

        logger.info("Fetching all reservables in the dataabse now ...");
        return reservableRepository.findAll();
    }

    /**
     * method that returns all possible rooms from.
     *
     * @return a list of rooms
     */
    public List<Room> getAllRooms() {
        List<Reservable> reservables = getAllReservables();

        logger.info("Finding al rooms from the fetched reservables");

        List<Room> rooms = new ArrayList<>();

        for (Reservable reservable : reservables) {
            if (reservable instanceof Room) {
                rooms.add((Room) reservable);
            }
        }

        return rooms;
    }

    /**
     * method that returns a list of all possible bikes.
     *
     * @return a list of bikes
     */
    public List<Reservable> getAllBikes() {
        List<Reservable> reservables = getAllReservables();
        List<Reservable> bikes = new ArrayList<>();
        for (Reservable reservable : reservables) {
            if (reservable instanceof Bike) {
                bikes.add(reservable);
            }
        }
        return bikes;
    }

    /**
     * This method finds all {@link Reservable} objects linked to a building via its map.
     * It first uses the {@link BuildingService} bean to fetch the building, while at the same
     * time checking whether it is still in the database.
     * If found, the map field of the constructed {@link Building} object is used to
     * get all {@link Reservable} objects in it.
     *
     * @param number    The id of the building to fetch.
     * @param type          The type of reservables to fetch.
     * @return A List of {@link Reservable} objects found in the map of the building.
     */
    public List<Reservable> getAllReservablesForBuilding(
        Long number, String type) {

        Optional<Building> buildingContainer = buildingService.getBuilding(number);

        if (buildingContainer.isEmpty()) {
            logger.info("Building " + number + " not found.");
            throw new EntityNotFoundException();
        }

        logger.info("Building " + number + " found. Fetching all its rooms ...");

        List<Reservable> rooms = new ArrayList<>();
        Map<Reservable, TimeSlot> reservables = buildingContainer.get().getAvailableTimeslots();
        for (Reservable reservable : reservables.keySet()) {
            if (type.equals("rooms")) {
                if (reservable instanceof Room) {
                    rooms.add(reservable);
                }
            } else if (reservable instanceof Bike) {
                rooms.add(reservable);
            }
        }

        logger.info("Constructing of list of building rooms completed.");
        return rooms;
    }

    /**
     * Gets an optional reservable.
     *
     * @param id for a reservable to search for
     * @return an optinal that can be a reservable or null if it doesn't exist
     */
    public Optional<Reservable> getReservable(Long id) {
        logger.info("Searching and fetching reservable " + id + " ...");
        return reservableRepository.findById(id);
    }

    /** Saves a new room or bike in the database. First checks whether the building of the new
     *      reservable exists and if so, saves the reservable using the bean
     *      {@link ReservableRepository}. Then adds the new reservable to the map of the building
     *      and generates a list of timeslots for the next two weeks for the new reservable.
     * @param reservable    The {@link Reservable} object to add to the database.
     * @param number        The number of the building to add the reservable to.
     */
    public void addReservable(Reservable reservable, Long number) {

        Optional<Building> buildingContainer = buildingService.getBuilding(number);

        if (buildingContainer.isEmpty()) {
            logger.info("Building " + number + " not found.");
            throw new EntityNotFoundException();
        }

        logger.info("Building " + number + " successfully found. Saving new reservable ...");

        reservableRepository.save(reservable);

        logger.info("Saving of new reservable successful. Adding the reservable to the map of"
            + " building " + number + " and generating timeslots for it.");
    }

    public void updateReservable(Long id, Reservable reservable) {
        reservableRepository.save(reservable);
    }

    /**
     * Deletes a Topic.
     *
     * @param id that identifies a reservable to be deleted
     */
    public void deleteReservable(Long id) {
        reservableRepository.deleteById(id);
    }

}
