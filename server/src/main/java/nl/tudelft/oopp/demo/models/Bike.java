package nl.tudelft.oopp.demo.models;

import javax.persistence.Entity;
import javax.persistence.Table;

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
    public Bike(Details details, Long id) {
        super(details, id);
    }
}
