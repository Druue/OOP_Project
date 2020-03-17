package nl.tudelft.oopp.api.models;


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
     * Empty Room constructor
     */
    public Room() {
        super();
    }

    /**
     * Constructor for Room object using all attributes
     * @param id
     * @param details
     * @param capacity
     * @param hasProjector
     * @param forEmployee
     */
    public Room(Long id, Details details, int capacity, boolean hasProjector, boolean forEmployee) {
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

}
