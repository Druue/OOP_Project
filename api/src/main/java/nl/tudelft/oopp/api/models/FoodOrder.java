package nl.tudelft.oopp.api.models;

import java.util.Collection;

/**
 * A {@link FoodOrder}.
 */
public class FoodOrder {

    /**
     * This is the id of the food order.
     */
    public Long id;

    /**
     * The ordered food.
     */
    public Collection<Food> food;

    /**
     * The user who made the order.
     */
    public User user;

    /**
     * The optional reservation that is tied to the food order.
     */
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




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Food> getFood() {
        return food;
    }

    public void setFood(Collection<Food> food) {
        this.food = food;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
