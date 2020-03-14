package nl.tudelft.oopp.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Initialises a new isntance of {@link Reservation}.
 */
@Entity
@Table(name = "Reservation")
public class Reservation {

    /**
     * Initialises a new isntance of {@link Reservation}.
     */
    public Reservation() {

    }

    /**
     * The ID of the user who made the reservation.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "netId")
    public Users userID;

    /**
     * This is a reservation of a specific reservable.
     */
    @OneToMany
    @Column(name="reservable")
    public Reservable reservable;

    /**
     * This shows the timeslot of a reservation.
     */
    @OneToOne
    @Column(name="timeslot")
    public TimeSlot timeslot;


    /**
     * The reservation's unique ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservation_id")
    public Long reservationID;


    public Reservation(Users userID, Reservable reservable, TimeSlot timeslot, Long reservationID) {
        this.userID=userID;
        this.reservable=reservable;
        this.timeslot=timeslot;
        this.reservationID=reservationID;
    }
}
