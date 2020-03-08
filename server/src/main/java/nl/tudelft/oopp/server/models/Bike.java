package nl.tudelft.oopp.server.models;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Initialises a new {@link Bike}.
 */
@Entity
@Table(name = "bike")
public class Bike extends Reservable {

    /**
     * Initialises a new {@link Bike}.
     * 
     * @param id          The bike's ID.
     * @param name        The bike's name.
     * @param isAvailable The bike's availability.
     */
    public Bike(Long id, String name, boolean isAvailable) {
        super(id, name, isAvailable);
    }
}
