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
    @Column(name = "building_number")
    One
    public Integer buildingNumber;

    @ElementCollection
    @Column(name = "menu")
    public Collection<MenuItem> menu;
}
