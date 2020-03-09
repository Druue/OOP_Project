package nl.tudelft.oopp.api.models;

public class RegistrationRequest {

    private String name;
    private String netID;
    private String email;
    private String password;
    private String role;

    /**
     * An Object used during the registration process, containing all of the user's details.
     * 
     * @param name     The user's name.
     * @param netID    The user's netID.
     * @param email    the user's email.
     * @param password the user's password.
     * @param role     the user's role: Either employee or student.
     */
    public RegistrationRequest(String name, String netID, String email, String password, String role) {
        this.name = name;
        this.netID = netID;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
