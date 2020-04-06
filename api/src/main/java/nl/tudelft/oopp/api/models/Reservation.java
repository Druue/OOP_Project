package nl.tudelft.oopp.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Initialises a new isntance of {@link Reservation}.
 */
public class Reservation {

    /**
     * The reservation's unique ID.
     */
    private Long reservationID;

    /**
     * The ID of the user who made the reservation.
     */
    private User user;

    /**
     * This is a reservation of a specific reservable.
     */
    private Reservable reservable;

    /**
     * This shows the timeslot of a reservation.
     */
    private TimeSlot timeslot;

    /**
     * Initialises a new instance of {@link Reservation}.
     */
    public Reservation() {

    }

    /**
     * Initialises a new instance of {@link Reservation}.
     *
     * @param reservationID The reservation's unique ID.
     * @param user          The user holding the reservation.
     * @param reservable    The entity being reserved.
     * @param timeslot      The time during which the entity will be reserved.
     */
    @JsonCreator
    public Reservation(
        @JsonProperty("reservationID") Long reservationID,
        @JsonProperty("user") User user,
        @JsonProperty("reservable") Reservable reservable,
        @JsonProperty("timeslot") TimeSlot timeslot) {
        this.user = user;
        this.reservable = reservable;
        this.timeslot = timeslot;
        this.reservationID = reservationID;
    }

    /**
     * Initialises a new instance of {@link Reservation} without ID.
     *
     * @param user          The user holding the reservation.
     * @param reservable    The entity being reserved.
     * @param timeslot      The time during which the entity will be reserved.
     */
    public Reservation(User user, Reservable reservable,
                       TimeSlot timeslot) {
        this.user = user;
        this.reservable = reservable;
        this.timeslot = timeslot;
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

    public TimeSlot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeSlot timeslot) {
        this.timeslot = timeslot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reservation that = (Reservation) o;
        return Objects.equals(getReservationID(), that.getReservationID())
               && Objects.equals(getUser(), that.getUser())
               && Objects.equals(getReservable(), that.getReservable())
               && Objects.equals(getTimeslot(), that.getTimeslot());
    }
}
