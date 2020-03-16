package nl.tudelft.oopp.server.models;

import javax.persistence.ElementCollection;
import javax.persistence.OneToMany;

/**
 * A piece of {@link Food}.
 */
public class Food {

    /**
     * This hold the details about the food item.
     */
    @OneToMany
    @ElementCollection
    public Details details;

}
