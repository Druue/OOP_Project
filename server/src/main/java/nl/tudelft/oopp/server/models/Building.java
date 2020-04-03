package nl.tudelft.oopp.server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
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
    private Long number;

    /**
     * The details of the building.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details", referencedColumnName = "id")
    private Details details;

    /**
     * The hours during which the building is open during the week.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "opening_hours", referencedColumnName = "id")
    private TimeSlot openingHours;

    /**
     * List of all rooms and bikes.
     */
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "building")
    private List<Reservable> reservables;

    /**
     * Initialises a new instance of {@link Building}.
     */
    public Building() {

    }

    /**
     * Initialises a new instance of {@link Building}.
     *  @param number             The building's number.
     * @param details            {@link Details} about the building.
     * @param openingHours       The building's {@link TimeSlot}.
     */
    public Building(Long number, Details details, TimeSlot openingHours) {
        this.number = number;
        this.details = details;
        this.openingHours = openingHours;
        this.reservables = new ArrayList<>();
    }

    /**
     * Initialises a new instance of {@link Building}.
     *
     * @param number             The building's number.
     * @param details            {@link Details} about the building.
     * @param openingHours       The building's {@link TimeSlot}.
     * @param reservables        The buidling's {@link Room} and {@link Bike}.
     */
    public Building(Long number, Details details, TimeSlot openingHours,
                    List<Reservable> reservables) {
        this.number = number;
        this.details = details;
        this.openingHours = openingHours;
        this.reservables = reservables;
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

    public TimeSlot getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(TimeSlot openingHours) {
        this.openingHours = openingHours;
    }

    @JsonIgnore
    public List<Reservable> getReservables() {
        return reservables;
    }

    public void setReservables(List<Reservable> reservables) {
        this.reservables = reservables;
    }
}
