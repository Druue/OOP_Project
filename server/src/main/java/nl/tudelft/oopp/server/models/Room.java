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
     * This denotes how many people can be in a room.
     */
    @Column(name = "capacity")
    public int capacity;
    /**
     * This lets the user know which room has a projector.
     */
    @Column(name = "hasProjector")
    public boolean hasProjector;

    /**
     * Whether the room is only reservable by staff or not.
     */
    @Column(name = "forEmployee")
    public boolean forEmployee;

}
