package nl.tudelft.oopp.server.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A piece of {@link Food}.
 */
@Entity
@Table(name = "food_item")
public class Food {

    @Id
    @GeneratedValue
    @Column(name = "food_id")
    public Long id;

    /**
     * This hold the details about the food item.
     */
    @Column(name = "details")
    public Details details;

}
