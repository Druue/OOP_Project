package nl.tudelft.oopp.server.models;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * A piece of {@link Food}.
 */
public class Food {

    @Id
    @GeneratedValue
    @Column(name = "food_id")
    public Long id;

    /**
     * This hold the details about the food item.
     */
    @OneToMany
    @ElementCollection
    public Details details;

}
