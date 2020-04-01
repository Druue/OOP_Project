package nl.tudelft.oopp.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A {@link Building}.
 */
public class Building {

    /**
     * The building's campus number works as a building id in the database.
     */
    public Long number;

    /**
     * The details of the building.
     */
    public Details details;

    /**
     * The foodcourt within the building.
     */
    public Foodcourt foodcourt;

    /**
     * The hours during which the building is open during the week.
     */
    public TimeSlot openingHours;

    /**
     * List with all the rooms and bikes.
     */
    @JsonIgnore
    List<Reservable> reservables;

    /**
     * Initialises a new instance of {@link Building}.
     */
    public Building() {

    }

    /**
     * Initialises a new instance of {@link Building}.
     *
     * @param number       The building's number.
     * @param details      {@link Details} about the building.
     * @param openingHours The building's {@link TimeSlot}.
     */
    public Building(Long number, Details details, TimeSlot openingHours) {
        this.number = number;
        this.details = details;
        this.openingHours = openingHours;
        reservables = new ArrayList<>();
    }

    /** Create a new building with the provided data.
     * @param number    The unique number of the building.
     * @param details   The details of the building, including its name, description, image.
     */
    public Building(Long number, Details details) {
        this.number = number;
        this.details = details;
        reservables = new ArrayList<>();
    }

    public String getName() {
        return details.getName();
    }

    public void setName(String name) {
        details.setName(name);
    }

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

    public Foodcourt getFoodcourt() {
        return foodcourt;
    }

    public void setFoodcourt(Foodcourt foodcourt) {
        this.foodcourt = foodcourt;
    }

    public TimeSlot getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(TimeSlot openingHours) {
        this.openingHours = openingHours;
    }

    public List<Reservable> getReservables() {
        return reservables;
    }

    public void setReservables(List<Reservable> reservables) {
        this.reservables = reservables;
    }
}
