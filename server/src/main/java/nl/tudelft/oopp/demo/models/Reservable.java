package nl.tudelft.oopp.demo.models;

abstract class Reservable {
	public long Id;
	public String Name;
	public Building Building;
	public boolean IsAvailable;

	public Reservable(long id, String name, Building building, boolean isAvailable) {
		this.Id = id;
		this.Name = name;
		this.Building = building;
		this.IsAvailable = isAvailable;
	}
}