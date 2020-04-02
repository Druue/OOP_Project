package nl.tudelft.oopp.server.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
    private Long id;

    /**
     * This is a details entity that tells you information about a reservable.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details")
    private Details details;

    @ManyToOne(cascade = CascadeType.MERGE) /* This way, the building is automatically updated if a change is made to it
                                            through the reservable*/
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private Building building;

    /**
     * Initialises a new instance of {@link Reservable}.
     */
    public Reservable() {

    }

    public Reservable(Long id, Details details) {
        this.id = id;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
