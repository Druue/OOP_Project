package nl.tudelft.oopp.api.models;

/**
 * Initialises a new instance of a {@link Reservable}.
 */
public abstract class Reservable {
    private Long id;
    private String name;
    private boolean isAvailable;

    public Reservable() {
    }

    /**
     * Initializes a new reservable.
     * 
     * @param id          Unique id of the reservable.
     * @param name        Name of the connected building.
     * @param isAvailable a boolean stating the availability of the reservable.
     */
    public Reservable(Long id, String name, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsAvailable() {
        return this.isAvailable;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Reservable id(Long id) {
        this.id = id;
        return this;
    }

    public Reservable name(String name) {
        this.name = name;
        return this;
    }

    public Reservable isAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
        return this;
    }
}
