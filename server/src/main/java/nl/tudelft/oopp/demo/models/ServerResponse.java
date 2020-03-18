package nl.tudelft.oopp.demo.models;

public class ServerResponse {
    private String message;
    private String role;

    /**
     * A Response object sent after a login attempt by a user.
     *
     * @param message the message that should be sent back.
     * @param role    the role of the user to be sent
     */
    public ServerResponse(String message, String role) {
        this.message = message;
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
