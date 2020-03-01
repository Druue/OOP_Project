package nl.tudelft.oopp.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Reservable.
 */
@Entity
@Table(name = "reservable")
abstract class Reservable {
    /**
     * The reservable's unique Id.
     */
    @Id
    @Column(name = "id")
    public long id;

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

    public Reservable(long id, String name, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.isAvailable = isAvailable;
    }
}
