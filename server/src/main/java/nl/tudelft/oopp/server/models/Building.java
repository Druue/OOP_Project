package nl.tudelft.oopp.server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
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
     * List of all rooms and bikes.
     */
    @JsonIgnore
    // @OneToMany(cascade = CascadeType.ALL)
    @ElementCollection
    List<Reservable> reservables;

    /**
     * Initialises a new instance of {@link Building}.
     */
    public Building() {

    }

    /**
     * Initialises a new instance of {@link Building}.
     *
     * @param number             The building's number.
     * @param details            {@link Details} about the building.
     * @param foodcourt          The building's {@link Foodcourt}.
     * @param openingHours       The building's {@link TimeSlot}.
     * @param reservables the buidling's map.
     */
    public Building(Long number, Details details, Foodcourt foodcourt, TimeSlot openingHours,
                    List<Reservable> reservables) {
        this.number = number;
        this.details = details;
        this.foodcourt = foodcourt;
        this.openingHours = openingHours;
        this.reservables = reservables;
    }

    /**returns the map of the building.
     *
     * @return the map
     */
    @JsonIgnore
    public List<Reservable> getMap() {
        return this.reservables;
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
