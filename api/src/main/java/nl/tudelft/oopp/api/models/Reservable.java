package nl.tudelft.oopp.api.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Room.class),
    @JsonSubTypes.Type(value = Bike.class)
})
public abstract class Reservable {

    /**
     * The reservable's unique Id.
     */

    private Long id;

    /**
     * This is a details entity that tells you information about a reservable.
     */
    private Details details;

    private Building building;

    public Reservable(Long id, Details details) {
        this.id = id;
        this.details = details;
    }

    public Reservable() {

    }

    public Reservable(Details details) {
        this.details = details;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reservable that = (Reservable) o;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getDetails(), that.getDetails())
               && Objects.equals(getBuilding(), that.getBuilding());
    }

}
