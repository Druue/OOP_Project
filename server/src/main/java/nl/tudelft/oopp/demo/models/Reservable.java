package nl.tudelft.oopp.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Reservable
 */
@Entity
@Table(name = "reservable")
abstract class Reservable {
	/**
	 * The reservable's unique Id.
	 */
	@Id
	@Column(name = "id")
	public long Id;

	/**
	 * The name of the building. EXAMPLE: "Ewi"
	 */
	@Column(name = "name")
	public String Name;

	/**
	 * Whether the current reservable object is available to be reserved.
	 */
	@Column(name = "isavailable")
	public boolean IsAvailable;

	public Reservable(long id, String name, boolean isAvailable) {
		this.Id = id;
		this.Name = name;
		this.IsAvailable = isAvailable;
	}
}