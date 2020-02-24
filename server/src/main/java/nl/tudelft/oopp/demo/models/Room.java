package nl.tudelft.oopp.demo.models;

/**
 * Room
 */
public class Room extends Reservable {
	public boolean EmployeeOnly;

	public Room(long id, String name, Building building, boolean isAvailable, boolean employeeOnly) {
		super(id, name, building, isAvailable);
		this.EmployeeOnly = employeeOnly;
	}

}