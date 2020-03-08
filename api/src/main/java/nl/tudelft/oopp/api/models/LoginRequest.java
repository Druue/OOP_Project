package nl.tudelft.oopp.api.models;

public class LoginRequest {

    private String netID;
    private String password;

    /**
     * An object containing a user's login information. Only used during the login process.
     * @param netID    the user's NetID.
     * @param password the user's password.
     */
    public LoginRequest(String netID, String password) {
        this.netID = netID;
        this.password = password;
    }

    public String getNetID() {
        return this.netID;
    }

    public void setNetID(String netID) {
        this.netID = netID;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
