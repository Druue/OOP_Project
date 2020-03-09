package nl.tudelft.oopp.api.models;

public class Room extends Reservable {
    private boolean employeeOnly;

    /**
     * Initializes a new room.
     * @param id          Unique id of the Room.
     * @param name        Name of the connected building.
     * @param isAvailable a boolean stating the availability of the room.
     * @param employeeOnly signifies whether only employees can reserve this room.
     */
    public Room(Long id, String name, boolean isAvailable, boolean employeeOnly) {
        super(id, name, isAvailable);
        this.employeeOnly = employeeOnly;
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
