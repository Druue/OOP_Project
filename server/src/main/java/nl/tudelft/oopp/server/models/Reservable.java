package nl.tudelft.oopp.server.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Initialises a new {@link Reservable}.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Room.class),
    @JsonSubTypes.Type(value = Bike.class)
})
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
}
