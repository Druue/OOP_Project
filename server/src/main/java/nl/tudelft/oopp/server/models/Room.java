package nl.tudelft.oopp.server.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Room.
 */
@Entity
@Table(name = "room")
public class Room extends Reservable {

    public Room() {
        super();
    }

    /**
     * Whether the room is only reservable by staff or not.
     */
    @Column(name = "employeeonly")
    public boolean employeeOnly;

    public Room(Long id, String name, boolean isAvailable, boolean employeeOnly) {
        super(id, name, isAvailable);
        this.employeeOnly = employeeOnly;
    }

    public Room(String name, boolean isAvailable, boolean employeeOnly) {
        super(name, isAvailable);
        this.employeeOnly = employeeOnly;
    }
}
