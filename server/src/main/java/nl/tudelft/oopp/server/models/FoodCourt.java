package nl.tudelft.oopp.demo.models;

import java.util.Collection;
import java.util.*;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Foodcourt.
 */
@Entity
@Table(name = "FoodCourt")
public class FoodCourt {

    /**
     * Initialises a new instance of {@link FoodCourt}.
     */
    public FoodCourt() {
    }

    /**
     * The menu of items available at the foodcourt.
     */
    @Id
    @Column(name = "id")

    public Integer buildingNumber;


    /**
     * This holds the details about each foodcourt in a building.
     */
    @JoinColumn(name = "Details")
    @OneToMany
    public Collection<Details> details;


    /**
     * This is a collection of food that the foodcourt serves
     */
    @ElementCollection
    @ManyToMany
    @CollectionTable(name="Foodlist")
    public Collection<Food> foodlist;

    /**
     * @param id
     * @param details
     * @param foodlist
     */
    public FoodCourt(Integer buildingNumber, Collection<Details> details, Collection<String> foodlist) {
        this.buildingNumber = buildingNumber;
        this.details = details;
        this.foodlist = new ArrayList<>();
    }
}
