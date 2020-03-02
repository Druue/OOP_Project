package nl.tudelft.oopp.demo.models;

import java.util.Collection;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Building.
 */
@Entity
@Table(name = "building")
public class Building {

    /**
     * Initialises a new instance of {@link Building}.
     */
    public Building() {
    }

    /**
     * The building's campus number.
     */
    @Id
    @Column(name = "number")
    public int number;

    /**
     * The name of the building.
     */
    @Column(name = "name")
    public String name;

    /**
     * The hours during which the building is open during the week.
     */
    @Column(name = "openinghours")
    public TimeSlot openingHours;

    /**
     * OPTIONAL: The foodcourt within the building.
     */
    @OneToOne
    @JoinColumn(name = "number", referencedColumnName = "building_number")
    public Foodcourt foodCourt;

    // /**
    // * A map of available reservation slots for each reservable entity at/in the
    // building.
    // */
    // @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // @CollectionTable(name = "available_reserverations")
    // @MapKeyColumn(name = "reservable")
    // Map<Reservable, TimeSlots> availableReservations;

    @ElementCollection
    @Column(name = "availablereservables")
    Collection<Reservable> reservables;
}
