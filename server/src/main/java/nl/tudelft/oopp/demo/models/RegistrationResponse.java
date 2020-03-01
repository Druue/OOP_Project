package nl.tudelft.oopp.demo.models;

public class RegistrationResponse {
    public String message;
    public String alertType;

    public RegistrationResponse(String message, String alertType) {
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
