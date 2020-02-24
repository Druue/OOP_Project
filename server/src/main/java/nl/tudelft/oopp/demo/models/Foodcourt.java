package nl.tudelft.oopp.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Foodcourt
 */
@Entity
@Table(name = "foodcourt")
public class Foodcourt {
	/**
	 * The menu of items available at the foodcourt.
	 */
	@Column(name = "menu")
	public Iterable<MenuItem> Menu;
}