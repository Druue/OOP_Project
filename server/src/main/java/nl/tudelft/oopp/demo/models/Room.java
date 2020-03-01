package nl.tudelft.oopp.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Room.
 */
@Entity
@Table(name = "room")
public class Room extends Reservable {

    /**
     * Whether the room is only reservable by staff or not.
     */
    @Column(name = "employeeonly")
    public boolean employeeOnly;

    public Room(long id, String name, boolean isAvailable, boolean employeeOnly) {
        super(id, name, isAvailable);
        this.employeeOnly = employeeOnly;
    }

}
