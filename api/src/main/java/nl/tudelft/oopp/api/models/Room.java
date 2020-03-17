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


    public Room() {
        super();
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

    public Room(String name, boolean isAvailable, boolean employeeOnly) {
        super(name, isAvailable);
        this.employeeOnly = employeeOnly;
    }

    public Room() {
        super();
    }


    public boolean isEmployeeOnly() {
        return employeeOnly;
    }

    public void setEmployeeOnly(boolean employeeOnly) {
        this.employeeOnly = employeeOnly;
    }


}
