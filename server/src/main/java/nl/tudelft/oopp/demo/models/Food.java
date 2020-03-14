package nl.tudelft.oopp.demo.models;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This is a food item and it stores details about itself
 */
public class Food {

    /**
     * This hold the details about a food item
     */
    @OneToMany
    @CollectionColumn(name="details")
    public Details details;

}
