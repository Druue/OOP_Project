package nl.tudelft.oopp.demo.models;

public class ServerResponse {
    String message;
    String alertType;

    /**
     * A Response object sent after a login attempt by a user.
     * @param message the message that should be sent back.
     * @param alertType the type of alert that should be used.
     */
    public ServerResponse(String message, String alertType) {
        this.message = message;
        this.alertType = alertType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

}
