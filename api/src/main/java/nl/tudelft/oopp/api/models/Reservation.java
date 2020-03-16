package nl.tudelft.oopp.api.models;

/**
 * Initialises a new isntance of {@link Reservation}.
 */
public class Reservation {

    public Long reservationID;
    public User user;
    public Reservable reservable;


    /**
     * Initialises a new {@link Reservation}.
     * 
     * @param reservationID The reservation's ID.
     * @param user          The user who made the reservation.
     * @param reservable    The entity being reserved.
     */
    public Reservation(Long reservationID, User user, Reservable reservable) {
        this.reservationID = reservationID;
        this.user = user;
        this.reservable = reservable;
    }

    public Long getReservationID() {
        return reservationID;
    }

    public void setReservationID(Long reservationID) {
        this.reservationID = reservationID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Reservable getReservable() {
        return reservable;
    }

    public void setReservable(Reservable reservable) {
        this.reservable = reservable;
    }
}
