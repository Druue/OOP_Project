package nl.tudelft.oopp.demo.models;


public class LoginDetails {

    private String NetID;
    private String password;


    public LoginDetails(String netID, String password) {
        NetID = netID;
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
