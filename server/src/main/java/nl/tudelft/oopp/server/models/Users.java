package nl.tudelft.oopp.demo.models;

import java.util.List;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ElementCollection;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Initialises a new {@link User}.
 */
@Entity
@Table(name = "Users")
public class Users {

    /**
     * The user's email.
     */
    @Column(name = "email")
    public String email;

    /**
     * The user's netID...?
     */
    @Id
    @Column(name = "netid")
    public String netId;

    /**
     * The user's password.
     */
    @Column(name = "password")
    public String password;
    /**
     * A collection of the user's current reservations.
     */

    @OneToMany
    public Collection<Details> details;

    /**
     * Initialises a new {@link User}.
     */
    public Users() {

    }

    /**
     * Creates a user from a provided registration details object.
     *
     * @param registrationDetails The registration details from which to create a new user
     */
    public Users(RegistrationDetails registrationDetails) {
        this.details = details;
        this.email = registrationDetails.getEmail();
        this.netId = registrationDetails.getNetID();
        this.password = registrationDetails.getPassword();
    }
}
