package nl.tudelft.oopp.server.models;

import java.util.Map;
import javax.persistence.*;

/**
 * A {@link Building}.
 */
@Entity
@Table(name = "Building")
public class Building {

    /**
     * The building's campus number works as a building id in the database.
     */
    @Id
    @Column(name = "id")
    public Long number;

    /**
     * The details of the building.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details", referencedColumnName = "id")
    public Details details;

    /**
     * The foodcourt within the building.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "foodCourt", referencedColumnName = "id")
    public Foodcourt foodcourt;

    /**
     * The hours during which the building is open during the week.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "opening_hours", referencedColumnName = "id")
    public TimeSlot openingHours;

    /**
     * Move this to the reservable table model.
     *
     */
    @OneToMany(cascade = CascadeType.ALL)
    @ElementCollection
    @CollectionTable(name = "available_TimeSlots")
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
     * @param availableTimeslots the buidling's map.
     */
    public Building(Long number, Details details, Foodcourt foodcourt, TimeSlot openingHours,
                    Map<Reservable, TimeSlot> availableTimeslots) {
        this.number = number;
        this.details = details;
        this.foodcourt = foodcourt;
        this.openingHours = openingHours;
        this.availableTimeslots = availableTimeslots;
    }
//
//    /**returns the map of the building.
//     *
//     * @return the map
//     */
//    public Map<Reservable, TimeSlot> getMap() {
//        return this.availableTimeslots;
//    }

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
