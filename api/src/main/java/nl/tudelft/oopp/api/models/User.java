package nl.tudelft.oopp.api.models;

import nl.tudelft.oopp.api.HttpRequestHandler;

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
     * The {@link UserKind} of user.
     */
    public UserKind userKind;

    /**
     * Initialises a new {@link User}.
     */
    public User() {

    }

    /**
     * Initialises a new {@link User}.
     * @param details       The user's details. (Includes the user's first and last name.)
     * @param email         The user's email.
     * @param username      The user's username.
     * @param password      The user's password.
     * @param userKind      The kind of user.
     */
    public User(Details details, String email, String username, String password, UserKind userKind) {
        this.details = details;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userKind = userKind;
    }

    public String getName() {
        return details.getName();
    }

    public void setName(String name) {
        details.setName(name);
    }

    /**
     * Creates a user from a provided registration details object.
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
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public UserKind getUserKind() {
        return userKind;
    }

    public void setUserKind(UserKind userKind) {
        this.userKind = userKind;
    }
}
