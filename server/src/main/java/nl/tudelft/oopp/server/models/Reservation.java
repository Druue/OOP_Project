package nl.tudelft.oopp.server.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Initialises a new isntance of {@link Reservation}.
 */
@Entity
@Table(name = "Reservation")
public class Reservation {

    /**
     * The reservation's unique ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservation_id")
    public Long reservationID;

    /**
     * The ID of the user who made the reservation.
     */
    @OneToOne
    @JoinColumn(name = "user", referencedColumnName = "user_id")
    public User user;

    /**
     * This is a reservation of a specific reservable.
     */
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY, property = "type")
    @JsonSubTypes ({
        @JsonSubTypes.Type(value = Room.class, name = "room"),
        @JsonSubTypes.Type(value = Bike.class, name = "bike")
    })
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservable", referencedColumnName = "id")
    public Reservable reservable;

    /**
     * This shows the timeslot of a reservation.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "timeslot", referencedColumnName = "id")
    public TimeSlot timeslot;

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
    public Reservation(Long reservationID, User user, Reservable reservable,
                       TimeSlot timeslot) {
        this.user = user;
        this.reservable = reservable;
        this.timeslot = timeslot;
        this.reservationID = reservationID;
    }
}
