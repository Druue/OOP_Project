package nl.tudelft.oopp.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Initialises a new {@link Reservable}.
 */
@Entity
@Table(name = "reservable")
public abstract class Reservable {

    /**
     * Initialises a new instance of {@link Reservable}.
     */
    public Reservable() {
    }

    /**
     * The reservable's unique Id.
     */
    @Id
    @Column(name = "reservable_id")
    public Long id;

    /**
     * The name of the building. EXAMPLE: "Ewi"
     */
    @Column(name = "name")
    public String name;

    /**
     * Whether the current reservable object is available to be reserved.
     */
    @Column(name = "isavailable")
    public boolean isAvailable;

    /**
     * Initialises a new {@link Reservable}.
     */
    public Reservable(Long id, String name, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.isAvailable = isAvailable;
    }
}
