package nl.tudelft.oopp.server.models;

import javax.persistence.*;
@Entity
@Table(name = "food_reservation")
public class FoodReservation {

    /**food_reservation_id to be unique for each one
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "food_reservation_id")
    public Long foodReservationId;

    /**attribute linked with a reservat
     *
     */
    @OneToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "reservation_id")
    public Reservation linkedWith;
}
