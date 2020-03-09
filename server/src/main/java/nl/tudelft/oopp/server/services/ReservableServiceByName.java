package nl.tudelft.oopp.server.services;

import java.util.Optional;
import nl.tudelft.oopp.server.models.Reservable;
import nl.tudelft.oopp.server.repositories.ReservableRepositoryByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservableServiceByName {

    @Autowired
    private ReservableRepositoryByName reservableRepositoryByName;

    /**
     * Gets an optional reservable.
     *
     * @param name for a reservable to search for
     * @return an optinal that can be a reservable or null if it doesn't exist
     */
    public Optional<Reservable> getReservable(String name) {
        return reservableRepositoryByName.findById(name);
    }

    /**
     * Updates a reservable.
     *
     * @param name         to be changed in a reservable
     * @param reservable to be changed
     */
    public void updateReservable(String name, Reservable reservable) {
        reservableRepositoryByName.save(reservable);
    }

    /**
     * Deletes a Topic.
     *
     * @param name that identifies a reservable to be deleted
     */
    public void deleteReservable(String name) {
        reservableRepositoryByName.deleteById(name);
    }

}

