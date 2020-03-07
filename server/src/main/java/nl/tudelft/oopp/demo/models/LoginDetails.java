package nl.tudelft.oopp.demo.models;


// This class is only used when a user logs in.
// The body of the login request is mapped to the fields of this class
// through the @ResponseBody annotation
// The information received is as follows:
// 1) The NetID of the user
// 2) The provided password in a hashed form, so as not to be stolen by a malicious user

public class LoginDetails {

    private String netID;
    private String password;


    /**
     * An object containing a user's login information. Only used during the login process.
     * 
     * @param netID    the user's NetID.
     * @param password the user's password.
     */
    public LoginDetails(String netID, String password) {

        this.netID = netID;
        this.password = password;
    }

    public String getNetID() {
        return netID;
    }

    public void setNetID(String netID) {
        this.netID = netID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
