package nl.tudelft.oopp.api.models;

/**
 * Initialises a new {@link Bike}.
 */
public class Bike extends Reservable {

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
