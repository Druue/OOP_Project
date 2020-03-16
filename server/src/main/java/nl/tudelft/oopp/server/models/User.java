package nl.tudelft.oopp.server.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.RegistrationDetails;

/**
 * Initialises a new {@link User}.
 */
@Entity
@Table(name = "User")
public class User {

    /**
     * The user's ID.
     */
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    @Id
    public Long id;

    /**
     * The user's email.
     */
    @Column(name = "email")
    public String email;

    /**
     * The user's netID.
     */
    @Column(name = "username")
    public String username;

    /**
     * The user's password.
     */
    @Column(name = "password")
    public String password;
    /**
     * A collection of the user's current reservations.
     */

    @OneToOne
    public Details details;

    /**
     * Initialises a new {@link User}.
     */
    public User() {

    }

    /**
     * Creates a user from a provided registration details object.
     *
     * @param registrationDetails The registration details from which to create a new user
     */
    public User(RegistrationDetails registrationDetails) {
        this.details =
                HttpRequestHandler.convertModel(registrationDetails.getDetails(), Details.class);
        this.email = registrationDetails.getEmail();
        this.username = registrationDetails.getNetID();
        this.password = registrationDetails.getPassword();
    }
}
