package nl.tudelft.oopp.api.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Room.
 */
public class Room extends Reservable {

    /**
     * This denotes how many people can be in a room.
     */
    public int capacity;
    /**
     * This lets the user know which room has a projector.
     */
    public boolean hasProjector;

    /**
     * Whether the room is only reservable by staff or not.
     */
    public boolean forEmployee;

    /**
     * Empty Room constructor.
     */
    public Room() {
        super();
    }

    /**
     * Constructor for Room object using all attributes.
     *
     * @param id           The room id.
     * @param details      The room details.
     * @param capacity     The room capacity.
     * @param hasProjector The boolean stating whether or not the room has a projector.
     * @param forEmployee  The boolean stating whether or not the room is for employees only.
     */
    @JsonCreator
    public Room(
        @JsonProperty("id") Long id,
        @JsonProperty("details") Details details,
        @JsonProperty("capacity") int capacity,
        @JsonProperty("hasProjector") boolean hasProjector,
        @JsonProperty("forEmployee") boolean forEmployee) {
        super(id, details);
        this.capacity = capacity;
        this.hasProjector = hasProjector;
        this.forEmployee = forEmployee;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

    public boolean isForEmployee() {
        return forEmployee;
    }

    public void setForEmployee(boolean forEmployee) {
        this.forEmployee = forEmployee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return getCapacity() == room.getCapacity()
               && isHasProjector() == room.isHasProjector()
               && isForEmployee() == room.isForEmployee();
    }

}
