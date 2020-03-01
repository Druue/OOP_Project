package nl.tudelft.oopp.demo.models;

import java.time.Period;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Building.
 */
@Entity
@Table(name = "building")
public class Building {

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
    public Period openingHours;

    /**
     * OPTIONAL: The foodcourt within the building.
     */
    @Column(name = "foodcourt")
    public Foodcourt foodCourt;

    /**
     * A map of available reservation slots for each reservable entity at/in the building.
     */
    @OneToMany
    @Column(name = "availablereservations")
    Map<Reservable, Iterable<Period>> availableReservations;

    @ElementCollection
    @Column(name = "availablereservables")
    Iterable<Reservable> reservables;
}
