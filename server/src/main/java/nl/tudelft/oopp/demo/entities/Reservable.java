package nl.tudelft.oopp.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Reservable")
public class Reservable {

    @Column(name = "name")
    private String name;
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "isAvailable")
    private boolean isAvailable;

    @Column(name = "buildingID")
    private long buildingID;

    public Reservable() {
    }

    /**
     * Create a new Reservable instance.
     *
     * @param The name of the reservable item to be used in the database
     * @param id Unique identifier as to be used in the database.
     * @param the availability of the item.
     * @param this identifies which building the reservable item is in.
     */
    public Reservable(String name, long id, boolean isAvailable, long buildingID) {
        this.name = name;
        this.id = id;
        this.isAvailable = isAvailable;
        this.buildingID = buildingID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public long getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(long buildingID) {
        this.buildingID = buildingID;
    }
}

