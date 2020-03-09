package nl.tudelft.oopp.server.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Initialises a new isntance of {@link Reservation}.
 */
@Entity
@Table(name = "reservation")
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
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    public User userID;

    @OneToOne
    @JoinColumn(name = "reservable_id", referencedColumnName = "reservable_id")
    public Reservable reservableId;
    /**
     * Initialises a new isntance of {@link Reservation}.
     */
    public Reservation() {

    }
}
