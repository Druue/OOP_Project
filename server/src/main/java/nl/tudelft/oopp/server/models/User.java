package nl.tudelft.oopp.server.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

    public User(Long id, String email, String username, String password, Details details,
                UserKind userKind) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.details = details;
        this.userKind = userKind;
    }
}
