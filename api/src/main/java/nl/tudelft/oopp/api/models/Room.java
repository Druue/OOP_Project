package nl.tudelft.oopp.api.models;

public class Room extends Reservable {
    private boolean employeeOnly;

    /**
     * Initializes new room
     * @param id          Unique id of the reservable
     * @param name        Name of the connected building
     * @param isAvailable
     * @param employeeOnly signifies whether only employees can reserve this room
     */
    public Room(Long id, String name, boolean isAvailable, boolean employeeOnly) {
        super(id, name, isAvailable);
        this.employeeOnly = employeeOnly;
    }
}
