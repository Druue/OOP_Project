package nl.tudelft.oopp.server.models;

import java.util.Collection;
import java.util.Map;
import javax.persistence.*;

/**
 * Building.
 */
@Entity
@Table(name = "building")
public class Building {
    public Building(String name, TimeSlot openingHours, FoodCourt foodCourt, Map<Reservable, TimeSlots> availableReservations, Collection<Reservable> reservables) {
        this.name = name;
        this.openingHours = openingHours;
        this.foodCourt = foodCourt;
        this.availableReservations = availableReservations;
        this.reservables = reservables;
    }

    /**
     * Initialises a new instance of {@link Building}.
     */
    public Building() {
    }

    /**
     * The building's campus number.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "number")
    public Integer number;

    /**
     * The name of the building.
     */
    @Column(name = "name")
    public String name;

    /**
     * The hours during which the building is open during the week.
     */
    @OneToOne
    @JoinColumn(name = "opening_hours", referencedColumnName = "id")
    public TimeSlot openingHours;

    /**
     * OPTIONAL: The foodcourt within the building.
     */
    @OneToOne
    @JoinColumn(name = "foodcourt", referencedColumnName = "building_number")
    public FoodCourt foodCourt;

    /**
     * A map of available reservation slots for each reservable entity at/in the building.
     */
    @OneToMany
    @ElementCollection
    @CollectionTable(name = "available_reserverations")
    Map<Reservable, TimeSlots> availableReservations;

    @ElementCollection
    @Column(name = "available_reservables")
    Collection<Reservable> reservables;
}
