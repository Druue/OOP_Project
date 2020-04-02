package nl.tudelft.oopp.api.models;

public abstract class Reservable {

    /**
     * The reservable's unique Id.
     */

    private Long id;

    /**
     * This is a details entity that tells you information about a reservable.
     */
    private Details details;

    private Building building;

    public Reservable(Long id, Details details) {
        this.id = id;
        this.details = details;
    }

    public Reservable() {

    }

    public Reservable(Details details) {
        this.details = details;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
