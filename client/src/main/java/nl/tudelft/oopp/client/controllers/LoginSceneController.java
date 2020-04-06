package nl.tudelft.oopp.client.controllers;

import java.io.IOException;
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
import nl.tudelft.oopp.client.MainApp;


public class LoginSceneController {
    private static final Logger LOGGER = Logger.getLogger(LoginSceneController.class.getName());
    public HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

    // the TextField object(s) from mainScene.fxml
    @FXML
    public TextField inputusername;
    @FXML
    public TextField inputPassword;

    public AlertsController alertsController;

    public LoginSceneController() {
        this.alertsController = new AlertsController();
    }

    public LoginSceneController(AlertsController alertsController) {
        this.alertsController = alertsController;
    }

    public UserAuthResponse sendLoginRequest(String username, String password) {
        LoginRequest loginRequest = new LoginRequest(username, password);
        return httpRequestHandler.post("login", loginRequest, UserAuthResponse.class);
    }


    /**
     * Entry function that gets called through the client.
     * Gets the input from the text fields and calls tryLogin().
     */
    public void tryLoginEntry() {
        String username = inputusername.getText();
        String password = inputPassword.getText();

        tryLogin(username, password);
    }

    /**
     * Sends a login request to the backend, using the information stored in the text fields.
     */
    public void tryLogin(String username, String password) {


        if (username.isEmpty() || password.isEmpty()) {

            alertsController.show(
                    Alert.AlertType.WARNING,
                    "Warning",
                    null,
                    "Please provide a username and password."

            );
        } else {
            // Try sending a request to the server.
            UserAuthResponse response = sendLoginRequest(username, password);

            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Response");
            alert.setHeaderText(null);
            if (response != null) {
                if (response.getAlertType().equals("CONFIRMATION")) {

                    // Saves the user gotten from the UserAuthResponse.
                    HttpRequestHandler.saveUser(response.getUser());

                    // After that, show the confirmation message retrieved from the backend.
                    alertsController.show(
                            Alert.AlertType.CONFIRMATION,
                            response.getMessage()
                    );

                    // Goes to the appropriate homepage based on type of user
                    if (response.getUser().getUserKind().equals(UserKind.Admin)) {
                        goToAdmin();
                    } else {
                        goToHomepage();
                    }
                } else {

                    //
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
