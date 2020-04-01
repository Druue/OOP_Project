package nl.tudelft.oopp.api.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Initialises a new {@link Bike}.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public class Bike extends Reservable {

    public Bike() {
        super();
    }

    /**
     * Initialises a new instance of {@link Bike}.
     *
     * @param id      The bike's ID.
     * @param details Details about the bike.
     */
    public Bike(Long id, Details details) {
        super(id, details);
    }

}
