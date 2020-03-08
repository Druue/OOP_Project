package nl.tudelft.oopp.server.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import nl.tudelft.oopp.server.models.Reservable;
import nl.tudelft.oopp.server.repositories.ReservableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservableService {

    @Autowired
    private ReservableRepository reservableRepository;

    /**
     * Gets a list of all reservables.
     * 
     * @return a list of all reservables
     */
    public List<Reservable> getAllReservables() {
        List<Reservable> reservables = new ArrayList<Reservable>();
        reservableRepository.findAll().forEach(reservables::add);
        return reservables;
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
