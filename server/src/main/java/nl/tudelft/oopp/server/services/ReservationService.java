package nl.tudelft.oopp.server.services;

import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.server.models.Reservation;
import nl.tudelft.oopp.server.repositories.ReservationRepository;
import nl.tudelft.oopp.server.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * returns a list with all reservations in the table.
     *
     * @return a list with reservations
     */
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        reservationRepository.findAll().forEach(reservations::add);
        return reservations;
    }


    /** This service method uses the reservation repository to get.
     *  all the reservations in the database, associated with a given user
     *
     * @param userID The id of the user whose reservations should be fetched from the database
     * @return A List of all the reservations of user with id = userID
     */
    public List<Reservation> getReservationsByUserID(Long userID) {
        return reservationRepository.findByUserID(userID);
    }

    /**
     * method adds this reservation to the tabel reservations.
     *
     * @param reservation to be added
     */
    public void addReservation(Reservation reservation) {

        reservationRepository.save(reservation);
    }

    /**
     * methods that updates this reservation id to the one in the parameter.
     *
     * @param id          linked to a reservation
     * @param reservation to be updated
     */
    public void updateReservation(Long id, Reservation reservation) {
        reservationRepository.save(reservation);
    }

    /**
     * method to delete a reservation with this ID.
     *
     * @param id linked to a reservation
     */
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

}
