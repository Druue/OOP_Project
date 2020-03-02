package nl.tudelft.oopp.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FoodItem.
 */
@Entity
@Table(name = "menu_item")
public class MenuItem { // Do we want to store this as an entity?
    @Id
    @Column(name = "id")
    public long id;

    @Column(name = "name")
    public String name;
}
