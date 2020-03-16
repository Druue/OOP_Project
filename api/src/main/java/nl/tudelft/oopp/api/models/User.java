package nl.tudelft.oopp.api.models;

/**
 * Initialises a new {@link User}.
 */
public class User {

    /**
     * The user's ID.
     */
    public Long id;

    /**
     * The user's email.
     */
    public String email;

    /**
     * The user's username.
     */
    public String username;

    /**
     * The user's password.
     */
    public String password;
    /**
     * A collection of the user's current reservations.
     */

    public Details details;

    /**
     * Initialises a new {@link User}.
     */
    public User() {

    }

    /**
     * Initialises a new {@link User}.
     * 
     * @param email    The user's email.
     * @param username The user's username.
     * @param password The user's password.
     * @param details  Details about the user.
     */
    public User(String email, String username, String password, Details details) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.details = details;
    }

    /**
     * Initialises a new {@link User}.
     *
     * @param registrationDetails The registration details from which to create a new user
     */
    public User(RegistrationDetails registrationDetails) {
        this.details = registrationDetails.getDetails();
        this.email = registrationDetails.getEmail();
        this.username = registrationDetails.getusername();
        this.password = registrationDetails.getPassword();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Details getDetails() {
        return this.details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public User id(Long id) {
        this.id = id;
        return this;
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    public User username(String username) {
        this.username = username;
        return this;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }

    public User details(Details details) {
        this.details = details;
        return this;
    }
}
