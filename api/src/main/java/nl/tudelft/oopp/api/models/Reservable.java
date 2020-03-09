package nl.tudelft.oopp.api.models;

public abstract class Reservable {
    private Long id;
    private String name;
    private boolean isAvailable;

    /**
     * Initializes a new reservable.
     * @param id Unique id of the reservable.
     * @param name Name of the connected building.
     * @param isAvailable a boolean stating the availability of the reservable.
     */
    public Reservable(Long id, String name, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.isAvailable = isAvailable;
    }
}
