package nl.tudelft.oopp.demo.models;


/**
 * Initialises a new {@link Bike}.
 */
public class Bike extends Reservable {

    public Bike(Long id, String name, boolean isAvailable) {
        super(id, name, isAvailable);
    }
}
