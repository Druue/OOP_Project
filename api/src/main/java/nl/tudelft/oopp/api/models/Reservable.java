package nl.tudelft.oopp.api.models;

import java.util.Objects;

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

    public Reservable(Long id, Details details) {
        this.id = id;
        this.details = details;
    }

    public Reservable() {

    }

    public Reservable(Details details) {
        this.details = details;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reservable that = (Reservable) o;
        return Objects.equals(getId(), that.getId())
               && Objects.equals(getDetails(), that.getDetails());
    }

}
