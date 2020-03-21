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
    @OneToOne
    @JoinColumn(name = "foodCourt", referencedColumnName = "id")
    public Foodcourt foodcourt;

    /**
     * The hours during which the building is open during the week.
     */
    @OneToOne
    @JoinColumn(name = "opening_hours", referencedColumnName = "id")
    public TimeSlot openingHours;

    /**
     * Move this to the reservable table model.
     *
     */
    @OneToMany
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

    /**returns the map of the building.
     *
     * @return the map
     */
    public Map<Reservable, TimeSlot> getMap() {
        return this.availableTimeslots;
    }
}
