package nl.tudelft.oopp.demo.models;


// This class is only used when a user logs in.
// The body of the login request is mapped to the fields of this class
// through the @ResponseBody annotation
// The information received is as follows:
// 1) The NetID of the user
// 2) The provided password in a hashed form, so as not to be stolen by a malicious user

public class LoginDetails {

    private String NetID;
    private String password;


    public LoginDetails(String NetID , String password) {

        this.NetID = NetID;
        this.password = password;
    }

    public String getNetID() {
        return NetID;
    }

    public void setNetID(String netID) {
        NetID = netID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
