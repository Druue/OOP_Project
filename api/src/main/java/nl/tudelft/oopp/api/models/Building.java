package nl.tudelft.oopp.api.models;

import java.util.Map;
import java.util.Objects;

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
     * @param openingHours The building's {@link TimeSlot}.
     */
    public Building(Long number, Details details, TimeSlot openingHours) {
        this.number = number;
        this.details = details;
        this.openingHours = openingHours;
    }

    public Building(Long number, Details details) {
        this.number = number;
        this.details = details;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Building building = (Building) o;
        return Objects.equals(getNumber(), building.getNumber())
               && Objects.equals(getDetails(), building.getDetails())
               && Objects.equals(getFoodcourt(), building.getFoodcourt())
               && Objects.equals(getOpeningHours(), building.getOpeningHours());
    }

}
