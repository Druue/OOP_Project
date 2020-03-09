package nl.tudelft.oopp.server.models;

import java.util.Collection;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The Building Table.
 */
@Entity
@Table(name = "building")
public class Building {
    /**
     * Creates a new Building.
     * @param name The building name.
     * @param openingHours A {@link TimeSlot} that includes the opening hours of the building.
     * @param foodCourt The building's foodcourt.
     * @param availableReservations A Map containing all {@link TimeSlots} that are available for reservation.
     * @param reservables The Collection containing all the reservables that a Building has.
     */
    public Building(String name, TimeSlot openingHours, FoodCourt foodCourt, Map<Reservable,
            TimeSlots> availableReservations, Collection<Reservable> reservables) {
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
