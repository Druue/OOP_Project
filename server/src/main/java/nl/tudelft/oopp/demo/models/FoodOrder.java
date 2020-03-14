package nl.tudelft.oopp.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FoodItem.
 */
@Entity
@Table(name = "Foodorder")
public class FoodOrder {

    public FoodOrder() {
    }

    /**
     * This is the id of the food order of a customer
     */
    @Id
    @Column(name = "id")
    public Long id;

    /**
     * This stores the actual information about the food they ordered
     */
    @ElementCollection
    public Collection<Food> food;

    /**
     * This has the information about the user that ordered the food.
     */
    @OneToMany
    @Column(name="user")
    public User user;


    /**
     * This stores the name of the food orered.
     */
    @Column(name = "name")
    public String name;

    public FoodOrder(Long id, Collection<Food> food, User user, String name) {
        this.id=id;
        this.food=food;
        this.user=user;
        this.name=name;
    }
}

