package nl.tudelft.oopp.server.models;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;

/**
 * A {@link Foodcourt}.
 */
@Entity
@Table(name = "foodcourt")
public class Foodcourt {

    /**
     * Initialises a new instance of {@link Foodcourt}.
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
    @OneToMany(cascade = CascadeType.ALL)
    public Collection<Details> details;


    /**
     * The foodcourt's menu.
     */
    @ElementCollection
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
