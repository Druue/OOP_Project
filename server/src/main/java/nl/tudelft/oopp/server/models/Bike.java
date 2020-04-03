package nl.tudelft.oopp.server.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Initialises a new {@link Bike}.
 */
@Entity
@Table(name = "Bike")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public class Bike extends Reservable {

    /**
     * A basic no-arg constructor for Bike.
     */
    public Bike() {
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
