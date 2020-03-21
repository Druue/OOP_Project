package nl.tudelft.oopp.server.models;

import javax.persistence.*;

/**
 * A piece of {@link Food}.
 */
@Entity
@Table(name = "food_item")
public class Food {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "food_id")
    public Long id;

    /**
     * This hold the details about the food item.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details", referencedColumnName = "id")
    public Details details;

}
