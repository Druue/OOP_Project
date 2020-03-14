package nl.tudelft.oopp.demo.models;

import nl.tudelft.oopp.demo.models.FoodCourt;
import nl.tudelft.oopp.demo.models.TimeSlots;

import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Building.
 */
@Entity
@Table(name = "Building")
public class Building {

    /**
     * Initialises a new instance of {@link Building}.
     */
    public Building() {
    }

    /**
     * The building's campus number works as a building id in the database.
     */
    @Id
    @Column(name = "id")
    public Long number;

    /**
     * The details of the building.
     */
    @Column(name = "details")
    public Details details;

    /**
     * OPTIONAL: The foodcourt within the building.
     */
    @OneToOne
    @Column(name = "foodcourt", referencedColumnName = "building_number", nullable="true")
    public FoodCourt foodCourt;

    /**
     * Should this be a column?
     */
    @Column(name="reservables")
    @OneToMany
    public Reservable reservable;

    /**
     * The hours during which the building is open during the week.
     */
    @OneToOne
    @JoinColumn(name = "opening_hours", referencedColumnName = "id")
    public OpeningTimes openingHours;



    /**
     * Move this to the reservable table model.
     *
     */
    @OneToMany
    @ElementCollection
    @CollectionTable(name = "available_TimeSlots")
    Map<Reservable, TimeSlots> availableTimeslots;


}
