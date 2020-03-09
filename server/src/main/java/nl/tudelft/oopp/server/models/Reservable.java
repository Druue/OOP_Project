package nl.tudelft.oopp.server.models;

import javax.persistence.*;

/**
 * Initialises a new {@link Reservable}.
 */
@Entity
@Table(name = "reservable")
@Inheritance(strategy = InheritanceType.JOINED)
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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Reservable(String name, boolean isAvailable) {
        this.name = name;
        this.isAvailable = isAvailable;
    }
}
