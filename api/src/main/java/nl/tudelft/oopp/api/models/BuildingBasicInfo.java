package nl.tudelft.oopp.api.models;

/**
 * A class containing only a subset of the {@link Building} class
 * fields. It is to be used when the client sends a request for basic
 * information about all database buildings. Then the JSON response containing
 * only the numbers, details and opening hours for every building should be mapped
 * to a list of instances of this class.
 */
public class BuildingBasicInfo {
    /**
     * The building's campus number works as a building id in the database.
     */
    public Long number;

    /**
     * The details of the building.
     */
    public Details details;

    /**
     * The hours during which the building is open during the week.
     */
    public TimeSlot openingHours;

    /** Construct a new {@link BuildingBasicInfo} instance with the provided arguments.
     * @param number The number of the building.
     * @param details The details of the building containing the name, description and image.
     * @param openingHours The {@link TimeSlot} object containing the opening hours of
     *                     the building.
     */
    public BuildingBasicInfo(Long number, Details details, TimeSlot openingHours) {

        this.number = number;
        this.details = details;
        this.openingHours = openingHours;
    }

    public BuildingBasicInfo() {}

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public TimeSlot getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(TimeSlot openingHours) {
        this.openingHours = openingHours;
    }
}
