package nl.tudelft.oopp.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import nl.tudelft.oopp.demo.models.Reservation;
import nl.tudelft.oopp.demo.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    /**returns a list with all reservations in the table.
    *
    * @return a list with reservations
    */
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        reservationRepository.findAll().forEach(reservations::add);
        return reservations;
    }

    /**method that returns a reservation if exists with this ID or null otherwise.
     *
     * @param ID linked to a reservation.
     * @return a reservation.
     */
    public Optional<Reservation> getReservations(Long ID) {
        return reservationRepository.findById(ID);
    }

    /**method adds this reservation to the tabel reservations.
     *
     * @param reservation to be added
     */
    public void addReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    /**methods that updates this reservation id to the one in the parameter.
     *
     * @param ID linked to a reservation
     * @param reservation to be updated
     */
    public void updateReservation(Long ID, Reservation reservation) {
        reservationRepository.save(reservation);
    }

    /**method to delete a reservation with this ID.
     *
     * @param ID linked to a reservation
     */
    public void deleteReservation(Long ID) {
        reservationRepository.deleteById(ID);
    }

}
