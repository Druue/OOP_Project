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

    public Reservable(String name, boolean isAvailable) {
        this.name = name;
        this.isAvailable = isAvailable;

    }

    public Reservable() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
