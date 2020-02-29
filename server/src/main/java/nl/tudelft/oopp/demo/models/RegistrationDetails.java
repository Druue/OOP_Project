package nl.tudelft.oopp.demo.models;


// This class represents the user information provided at registration time
public class RegistrationDetails {

    private String firstName;
    private String lastName;
    private String netID;
    private String email;
    private int password;
    private String role;


    public RegistrationDetails(String firstName, String lastName, String netID, String email, int password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.netID = netID;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNetID() {
        return netID;
    }

    public void setNetID(String netID) {
        netID = netID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
