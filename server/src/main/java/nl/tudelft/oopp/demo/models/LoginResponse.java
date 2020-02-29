package nl.tudelft.oopp.demo.models;
public class LoginResponse {
    String message;
    String alertType;
    String role;

    public LoginResponse(String message, String alertType, String role) {
        this.message = message;
        this.alertType = alertType;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
