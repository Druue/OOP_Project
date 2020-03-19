package nl.tudelft.oopp.server.models;

import javax.persistence.*;

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
     * The user's username.
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details", referencedColumnName = "id")
    public Details details;

    @Column(name = "userkind")
    public UserKind userKind;

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
        this.username = registrationDetails.getusername();
        this.password = registrationDetails.getPassword();
    }
}
