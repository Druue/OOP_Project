package nl.tudelft.oopp.api.models;

public class LoginRequest {

    private String username;
    private String password;

    public LoginRequest() {
    }

    /**
     * An object containing a user's login information. Only used during the login process.
     * @param username    the user's Username.
     * @param password the user's password.
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
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
}
