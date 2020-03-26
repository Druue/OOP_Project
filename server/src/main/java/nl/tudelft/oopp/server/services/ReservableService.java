package nl.tudelft.oopp.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import nl.tudelft.oopp.server.models.Bike;
import nl.tudelft.oopp.server.models.Reservable;
import nl.tudelft.oopp.server.models.Room;
import nl.tudelft.oopp.server.repositories.ReservableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class ReservableService {

    private final ReservableRepository reservableRepository;
    Logger logger = LoggerFactory.getLogger(ReservableService.class);

    public ReservableService(ReservableRepository reservableRepository) {
        this.reservableRepository = reservableRepository;
    }

    /**
     * Gets a list of all reservables.
     *
     * @return a list of all reservables
     */
    public List<Reservable> getAllReservables() {
        return reservableRepository.findAll();
    }

    /**
     * method that returns all possible rooms from.
     *
     * @return a list of rooms
     */
    public List<Room> getAllRooms() {
        List<Reservable> reservables = getAllReservables();

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
     * Gets an optional reservable.
     *
     * @param id for a reservable to search for
     * @return an optinal that can be a reservable or null if it doesn't exist
     */
    public Optional<Reservable> getReservable(Long id) {
        return reservableRepository.findById(id);
    }

    /**
     * Adds a reservable.
     *
     * @param reservable to be added to the list of reservables
     */
    public void addReservable(Reservable reservable) {
        reservableRepository.save(reservable);
    }

    public void addRoom(Room room) {
        reservableRepository.save(room);
    }

    /**
     * Updates a reservable.
     *
     * @param id         to be changed in a reservable
     * @param reservable to be changed
     */
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
