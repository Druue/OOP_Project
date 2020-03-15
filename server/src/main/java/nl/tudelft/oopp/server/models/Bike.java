package nl.tudelft.oopp.server.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;

/**
 * Initialises a new {@link Bike}.
 */
@Entity
@Table(name = "Bike")
public class Bike extends Reservable {

    /**
     * @param details
     * @param id
     */
    public Bike(Collection<Details> details, Long id) {
        super(details, id);
    }
}
