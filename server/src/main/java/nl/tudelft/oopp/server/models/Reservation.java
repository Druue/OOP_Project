package nl.tudelft.oopp.server.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Initialises a new isntance of {@link Reservation}.
 */
@Entity
@Table(name = "reservation")
public class Reservation {

    /**
     * Initialises a new isntance of {@link Reservation}.
     */
    public Reservation() {

    }

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
    @Column(name = "user_id")
    public Integer userID;
}
