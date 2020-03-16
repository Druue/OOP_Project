package nl.tudelft.oopp.api.models;


// This class represents the user information provided at registration time
public class RegistrationDetails {

    private Details details;
    private String username;
    private String email;
    private String password;

    /**
     * An Object used during the registration process, containing all of the user's details.
     *
     * @param details  The user's details.
     * @param username The user's username.
     * @param email    the user's email.
     * @param password the user's password.
     */
    public RegistrationDetails(Details details, String username, String email, String password) {
        this.details = details;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
