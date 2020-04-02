package nl.tudelft.oopp.server.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Details")
public class Details {

    /**
     * This is the id of the detail that is a randomly generated value.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long id;

    /**
     * This is the name of the detail.
     */
    @Column(name = "name")
    public String name;

    /**
     * This is the description of the detail.
     */
    @Column(name = "description")
    private String description;

    /**
     * This is the image of the detail.
     */
    @Column(name = "image")
    private String image;

    public Details() {

    }

    /**
     * Initialises a new instance of a {@link Details}.
     *
     * @param name        The name of the object.
     * @param description A description of the object.
     * @param image       An image of the object.
     */
    public Details(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
