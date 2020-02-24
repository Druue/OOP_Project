package nl.tudelft.oopp.demo.models;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * FoodItem
 */
@Entity
@Table(name = "MenuItem")
public class MenuItem { // Do we want to store this as an entity?
	public String Name;
}