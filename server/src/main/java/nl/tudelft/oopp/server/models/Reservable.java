package nl.tudelft.oopp.server.models;

import javax.persistence.*;

/**
 * Initialises a new {@link Reservable}.
 */
@Entity
@Table(name = "Reservable")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Reservable {

    /**
     * The reservable's unique Id.
     */
    @Id
    @Column(name = "id")
    public Long id;

    /**
     * This is a details entity that tells you information about a reservable.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @ElementCollection
    @JoinColumn(name = "details")
    public Details details;

    /**
     * Initialises a new instance of {@link Reservable}.
     */
    public Reservable() {

    }

    public Reservable(Long id, Details details) {
        this.id = id;
        this.details = details;
    }
}
