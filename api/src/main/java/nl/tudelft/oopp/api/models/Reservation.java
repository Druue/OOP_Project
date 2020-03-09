package nl.tudelft.oopp.api.models;

/**
 * Initialises a new instance of {@link Reservation}.
 */

public class Reservation {

    public Long reservationID;


    public User userID;

    public Reservable reservableId;

    public Reservation(Long reservationID, User userID, Reservable reservableId) {
        this.reservationID = reservationID;
        this.userID = userID;
        this.reservableId = reservableId;
    }

    public Long getReservationID() {
        return reservationID;
    }

    public void setReservationID(Long reservationID) {
        this.reservationID = reservationID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Reservable getReservableId() {
        return reservableId;
    }

    public void setReservableId(Reservable reservableId) {
        this.reservableId = reservableId;
    }
}
