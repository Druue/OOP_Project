package nl.tudelft.oopp.server.models;


import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * A {@link FoodOrder}.
 */
@Entity
@Table(name = "Foodorder")
public class FoodOrder {

    /**
     * This is the id of the food order.
     */
    @Id
    @Column(name = "id")
    public Long id;

    /**
     * The ordered food.
     */
    @ElementCollection
    public Collection<Food> food;

    /**
     * The user who made the order.
     */
    @OneToOne
    @JoinColumn(name = "user", referencedColumnName = "user_id")
    public User user;

    /**
     * The optional reservation that is tied to the food order.
     */
    @OneToOne
    @JoinColumn(name = "reservation", referencedColumnName = "reservation_id")
    public Reservation reservation;

    /**
     * Initialises a new instance of {@link FoodOrder}.
     */
    public FoodOrder() {

    }

    /**
     * Initialises a new instance of {@link FoodOrder}.
     *
     * @param id   The foodorder's id.
     * @param food The collection of food being ordered.
     * @param user The user who made the order.
     * @param reservation the reservation that is tied to this order.
     */
    public FoodOrder(Long id, Collection<Food> food, User user, Reservation reservation) {
        this.id = id;
        this.food = food;
        this.user = user;
        this.reservation = reservation;
    }
}
