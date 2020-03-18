package nl.tudelft.oopp.server.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import nl.tudelft.oopp.server.models.Reservable;
import nl.tudelft.oopp.server.models.Reservation;
import nl.tudelft.oopp.server.models.TimeslotAlreadyReservedException;
import nl.tudelft.oopp.server.models.UserReservationsIntersectionException;
import nl.tudelft.oopp.server.repositories.ReservationRepository;
import nl.tudelft.oopp.server.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
    }

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(new Date(System.currentTimeMillis()).getTime());
    }

    /**
     * returns a list with all reservations in the table.
     *
     * @return a list with reservations
     */
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    /**
     * Uses the reservationRepository bean to find the currently ongoing reservations.
     * It uses a timestamp half an hour ago as a starting point to make sure some
     * currently started reservations are not missed.
     *
     * @return A {@link List} of all current reservations
     */
    public List<Reservation> getAllCurrentReservations() {
        return reservationRepository.findAllCurrent(getCurrentTimestamp());
    }

    /**
     * This service method uses the reservation repository to get.
     * all the reservations in the database, associated with a given user
     *
     * @param userID The id of the user whose reservations should be fetched from the database
     * @return A List of all the reservations of user with id = userID
     */
    public List<Reservation> getReservationsByUserID(Long userID) {
        return reservationRepository.findAllByUser_Id(userID);
    }

    /**
     * Finds the currently ongoing reservations of a user.
     *
     * @param userID The id of the user whose reservations to find.
     * @return A {@link List} of the current user reservations.
     */
    public List<Reservation> getCurrentUserReservations(Long userID) {
        return reservationRepository.findAllCurrentByUser(userID, getCurrentTimestamp());
    }

    /** Checks whether there exist a reservation of the provided user in the given period.
     * @param userID The id of the user to check the reservations of.
     * @param startTime The start time of the period to search for reservations in.
     * @param endTime The end time of the period to search for reservations in.
     * @return True if such a reservation in the given period is found. False otherwise.
     */
    public boolean checkExistUserReservationsForPeriod(Long userID, Timestamp startTime,
                                                       Timestamp endTime) {
        List<Reservation> list = reservationRepository.findAllForUserForPeriod(userID,
            startTime, endTime);

        return list == null || list.isEmpty();
    }

    /**
     * Finds the current reservations related to the given reservable.
     *
     * @param reservable The reservable to  find the related reservations of.
     * @return A {@link List} of the current reservations having the given reservable as a field.
     */
    public List<Reservation> getCurrentReservableReservations(Reservable reservable) {
        return reservationRepository.findAllCurrentForReservable(reservable, getCurrentTimestamp());
    }

    /** Checks whether there exist a reservation of the provided reservable in the given period.
     * @param reservable The reservable to check the reservations of.
     * @param startTime The start time of the period to search for reservations in.
     * @param endTime The end time of the period to search for reservations in.
     * @return True if such a reservation in the given period is found. False otherwise.
     */
    public boolean checkExistReservableReservationsForPeriod(Reservable reservable,
                                                             Timestamp startTime,
                                                             Timestamp endTime) {
        List<Reservation> list = reservationRepository.findAllForReservableForPeriod(reservable,
            startTime, endTime);

        return list == null || list.isEmpty();
    }

    /**
     * Adds a new reservation the database if there are no intersections between
     * its timeslot and the timeslots of the other current reservations of the user.
     *
     * @param reservation The reservation to add to the database
     */
    public void addReservation(Reservation reservation) {

        checkReservableAlreadyReserved(reservation, reservation.reservable);
        checkReservationIntersection(reservation, reservation.user.id);

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

    /**
     * Checks whether there is an intersection between the timeslot of a new user
     * reservation and the current reservations for the same reservable. First takes
     * the start time and end time of the reservation's timeslot and then checks for
     * intersection with the other reservations.
     *
     * @param reservation The new reservation to check.
     * @param userID      The id of the user who adds this reservation.
     * @throws UserReservationsIntersectionException Throws it if intersection is found between
     *                                               the timeslots.
     */
    private void checkReservationIntersection(Reservation reservation, Long userID)
        throws UserReservationsIntersectionException {

        Timestamp newReservationStartTime = reservation.timeslot.startTime;
        Timestamp newReservationEndTime = reservation.timeslot.endTime;

        if (checkExistUserReservationsForPeriod(userID,
            newReservationEndTime, newReservationEndTime)) {
            LoggerService.error(ReservationService.class,
                "Intersection found. Cannot add new user reservations.");
            throw new UserReservationsIntersectionException();
        }

    }

    /**
     * Checks whether there are intersections between the timeslot of a new reservation
     * and the other current reservations for the same reservable.
     *
     */
    private void checkReservableAlreadyReserved(Reservation reservation,
                                                Reservable reservable)
        throws TimeslotAlreadyReservedException {

        // Take the start time of the first and the end time of the last
        Timestamp startTime = reservation.timeslot.startTime;
        Timestamp endTime = reservation.timeslot.endTime;

        if (checkExistReservableReservationsForPeriod(reservable, startTime, endTime)) {
            LoggerService.error(ReservationService.class,
                "Intersection found. Cannot add new reservation for this reservable.");
            throw new TimeslotAlreadyReservedException();
        }
    }


}
