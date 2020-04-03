package nl.tudelft.oopp.server.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Room.
 */
@Entity
@Table(name = "room")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public class Room extends Reservable {

    @JsonCreator
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
