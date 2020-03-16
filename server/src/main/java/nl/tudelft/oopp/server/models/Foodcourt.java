package nl.tudelft.oopp.server.models;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * A {@link Foodcourt}.
 */
@Entity
@Table(name = "foodcourt")
public class Foodcourt {

    /**
     * Initialises a new instance of {@link FoodCourt}.
     */
    public Foodcourt() {
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
    @JoinColumn(name = "details")
    @OneToMany
    public Collection<Details> details;


    /**
     * The foodcourt's menu.
     */
    @ElementCollection
    @ManyToMany
    @CollectionTable(name = "foodlist")
    public Collection<Food> menu;

    /**
     * Initialises a new instance of a {@link Foodcourt}.
     * 
     * @param buildingNumber The foodcourt's ID.
     * @param details        Details about the foodcourt.
     * @param menu           The foodcourt's menu.
     */
    public Foodcourt(Integer buildingNumber, Collection<Details> details, Collection<String> menu) {
        this.buildingNumber = buildingNumber;
        this.details = details;
        this.menu = new ArrayList<>();
    }
}
