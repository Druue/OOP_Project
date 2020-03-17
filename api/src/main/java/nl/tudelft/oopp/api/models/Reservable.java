package nl.tudelft.oopp.api.models;

/**
 * Initialises a new {@link Reservable}.
 */
public abstract class Reservable {

    /**
     * The reservable's unique Id.
     */

    public Long id;

    /**
     * This is a details entity that tells you information about a reservable.
     */
    public Details details;


    public Reservable() { }

    public Reservable(Long id, Details details) {
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
