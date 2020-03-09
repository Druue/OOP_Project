package nl.tudelft.oopp.server.models;

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
@Table(name = "foodcourt")
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
    @Column(name = "building_number")
    public Integer buildingNumber;

    @ElementCollection
    @Column(name = "menu")
    public Collection<MenuItem> menu;
}
