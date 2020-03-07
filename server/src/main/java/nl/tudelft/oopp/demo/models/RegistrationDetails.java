package nl.tudelft.oopp.demo.models;


// This class represents the user information provided at registration time
public class RegistrationDetails {

    private String username;
    private String netID;
    private String email;
    private String password;

    /**
     * An Object used during the registration process, containing all of the user's details.
     *
     * @param name     The user's name.
     * @param netID    The user's netID.
     * @param email    the user's email.
     * @param password the user's password.
     */
    public RegistrationDetails(String name, String netID, String email, String password) {
        this.username = name;
        this.netID = netID;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getNetID() {
        return netID;
    }

    public void setNetID(String netID) {
        this.netID = netID;
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
