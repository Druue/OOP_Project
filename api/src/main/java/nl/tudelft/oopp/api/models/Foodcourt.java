package nl.tudelft.oopp.api.models;

import java.util.ArrayList;
import java.util.Collection;


/**
 * A {@link Foodcourt}.
 */
public class Foodcourt {

    /**
     * Initialises a new instance of {@link Foodcourt}.
     */
    public Foodcourt() {
    }

    /**
     * The menu of items available at the foodcourt.
     */
    public Integer buildingNumber;


    /**
     * This holds the details about each foodcourt in a building.
     */
    public Collection<Details> details;


    /**
     * The foodcourt's menu.
     */
    public Collection<Food> menu;

    /**
     * Initialises a new instance of a {@link Foodcourt}.
     *
     * @param buildingNumber The foodcourt's ID.
     * @param details        Details about the foodcourt.
     * @param menu           The foodcourt's menu.
     */
    public Foodcourt(Integer buildingNumber, Collection<Details> details, Collection<Food> menu) {
        this.buildingNumber = buildingNumber;
        this.details = details;
        this.menu = menu;
    }

    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(Integer buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Collection<Details> getDetails() {
        return details;
    }

    public void setDetails(Collection<Details> details) {
        this.details = details;
    }

    public Collection<Food> getMenu() {
        return menu;
    }

    public void setMenu(Collection<Food> menu) {
        this.menu = menu;
    }
}
