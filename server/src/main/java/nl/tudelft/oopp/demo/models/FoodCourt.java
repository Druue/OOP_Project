package nl.tudelft.oopp.demo.models;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
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
    @Column(name = "Details")
    @OneToMany
    public Details details;


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
    public FoodCourt(Integer id, Details details, Collection<String> foodlist) {
        this.id = id;
        this.details = details;
        this.foodlist = foodlist;
    }
}
