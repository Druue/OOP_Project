package nl.tudelft.oopp.api.models;

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
     * Move this to the reservable table model.
     *
     */
    Map<Reservable, TimeSlot> availableTimeslots;

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
     * @param foodcourt    The building's {@link Foodcourt}.
     * @param openingHours The building's {@link TimeSlot}.
     */
    public Building(Long number, Details details, Foodcourt foodcourt, TimeSlot openingHours) {
        this.number = number;
        this.details = details;
        this.foodcourt = foodcourt;
        this.openingHours = openingHours;
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

    public Map<Reservable, TimeSlot> getAvailableTimeslots() {
        return availableTimeslots;
    }

    public void setAvailableTimeslots(Map<Reservable, TimeSlot> availableTimeslots) {
        this.availableTimeslots = availableTimeslots;
    }
}
