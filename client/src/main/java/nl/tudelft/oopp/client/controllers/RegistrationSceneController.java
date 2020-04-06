package nl.tudelft.oopp.client.controllers;

import java.io.IOException;
import java.util.logging.Level;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.Details;
import nl.tudelft.oopp.api.models.User;
import nl.tudelft.oopp.api.models.UserAuthResponse;
import nl.tudelft.oopp.api.models.UserKind;
import nl.tudelft.oopp.client.MainApp;


public class RegistrationSceneController {

    public HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

    @FXML
    public TextField registrationNameInput;
    public TextField registrationUsernameInput;
    public TextField registrationEmailInput;
    public PasswordField registrationPasswordInput;

    public AlertsController alertsController;

    public RegistrationSceneController() {
        this.alertsController = new AlertsController();
    }

    public RegistrationSceneController(AlertsController alertsController) {
        this.alertsController = alertsController;
    }

    /**
     * Handles going back to the login page.
     */
    public void goToLogin() {
        try {
            MainApp.goToPage("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Makes a request to the backend using the information that is present in the client's text
     * fields.
     */
    public void attemptRegistrationEntry() {

        // Get all text from text fields
        String username = registrationUsernameInput.getText();
        String password = registrationPasswordInput.getText();
        String email = registrationEmailInput.getText();
        String name = registrationNameInput.getText();

        attemptRegistration(username, password, email, name);
    }

    /**
     * Makes a request to the backend using the information that is present in the client's text
     * fields.
     */
    public void attemptRegistration(String username, String password, String email, String name) {

        // If any of these fields are empty: Send an alert.
        if (username.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty()) {
            alertsController.show(
                    Alert.AlertType.WARNING,
                    "Warning",
                    null,
                    "Please fill in all required fields."
            );
        } else {

            // Checks for the kind of user that is registering
            UserKind userKind = null;
            try {

                String domainEmailPart = email.split("@")[1];
                String userRole = domainEmailPart.split("\\.")[0];

                switch (userRole) {
                    case "student":

                        userKind = UserKind.Student;

                        break;
                    case "tudelft":

                        userKind = UserKind.Employee;

                        break;
                    case "admin":

                        userKind = UserKind.Admin;

                        break;
                    default:
                        throw new Exception();
                }
            } catch (Exception e) {

                alertsController.show(
                        Alert.AlertType.ERROR,
                        "ERROR",
                        null,
                        "Invalid email address given."
                );
                return;
            }

            User registrationRequest = new User(
                    new Details(name, null, null),
                    email, username, password, userKind);

            // Send a register request to the server.
            UserAuthResponse response = httpRequestHandler.post("register", registrationRequest,
                    UserAuthResponse.class);


            if (response != null) {
                if (response.getAlertType().equals("CONFIRMATION")) {

                    alertsController.show(
                            Alert.AlertType.CONFIRMATION,
                            response.getMessage()
                    );

                    // Save the user.
                    HttpRequestHandler.saveUser(response.getUser());

                    // Go to the login page.
                    goToLogin();
                } else {

                    alertsController.show(
                            Alert.AlertType.ERROR,
                            response.getMessage()
                    );

                }
            } else {

                alertsController.show(
                        Alert.AlertType.ERROR,
                        "Invalid response from server."
                );

            }
        }
    }
}
