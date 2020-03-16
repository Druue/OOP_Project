package nl.tudelft.oopp.api.models;


/**
 * A piece of {@link Food}.
 */

public class Food {

    public Long id;

    /**
     * This hold the details about the food item.
     */
    public Details details;

    public Food(Long id, Details details) {
        this.id = id;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
}
