package nl.tudelft.oopp.client.controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.Details;
import nl.tudelft.oopp.api.models.LoginRequest;
import nl.tudelft.oopp.api.models.User;
import nl.tudelft.oopp.api.models.UserAuthResponse;
import nl.tudelft.oopp.api.models.UserKind;
import nl.tudelft.oopp.client.AlertService;
import nl.tudelft.oopp.client.MainApp;


public class LoginSceneController {
    private static final Logger LOGGER = Logger.getLogger(LoginSceneController.class.getName());
    private static final HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

    @FXML
    public TextField inputusername;
    @FXML
    public TextField inputPassword;

    /**
     * Sends a login request to the backend, using the information stored in the text fields.
     */
    public void tryLogin() {

        String username = inputusername.getText();
        String password = inputPassword.getText();
        if (username.isEmpty() || password.isEmpty()) {

            AlertService.alertWarning(
                "Warning",
                "Please provide a username and password.");

        } else {
            LoginRequest loginRequest = new LoginRequest(username, password);
            UserAuthResponse response =
                httpRequestHandler.post("login", loginRequest, UserAuthResponse.class);

            if (response != null) {
                if (response.getAlertType().equals("CONFIRMATION")) {

                    // Saves the user gotten from the UserAuthResponse.
                    // This includes the user's id and details for a more personalized experience,
                    // and to make queries easier later on.
                    HttpRequestHandler.saveUser(response.getUser());

                    // Show an alert with the server response message.
                    AlertService.alertConfirmation("Response", response.getMessage());

                    // Goes to the appropriate homepage based on type of user
                    if (response.getUser().getUserKind().equals(UserKind.Admin)) {
                        goToAdmin();
                    } else {
                        goToHomepage();
                    }
                } else {
                    AlertService.alertError("Response", response.getMessage());
                }
            } else {
                AlertService.alertError("Response", "Invalid response from server!");

            }
        }
    }


    /**
     * Saves the current user as guest and continues to homepage.
     */
    public void continueAsGuest(ActionEvent event) {
        HttpRequestHandler.saveUser(new User(new Details("guest", null, null),
            null, "guest", null, UserKind.Guest));
        goToHomepage();
    }

    /**
     * Handles going the the user homepage.
     */
    public void goToHomepage() {
        try {
            MainApp.goToPage("homepage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles going to the admin homepage.
     */
    public void goToAdmin() {
        try {
            MainApp.goToPage("admin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles going to the registration page.
     */
    public void goToRegistration() {
        try {
            MainApp.goToPage("registration");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
