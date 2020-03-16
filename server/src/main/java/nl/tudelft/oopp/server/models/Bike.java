package nl.tudelft.oopp.server.models;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Initialises a new {@link Bike}.
 */
@Entity
@Table(name = "Bike")
public class Bike extends Reservable {

    /**
     * Initialises a new instance of {@link Bike}.
     * 
     * @param details Details about the bike.
     * @param id      The bike's ID.
     */
    public Bike(Collection<Details> details, Long id) {
        super(details, id);
    }
}
