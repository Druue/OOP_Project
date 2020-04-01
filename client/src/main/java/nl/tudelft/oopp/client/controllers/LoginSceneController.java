package nl.tudelft.oopp.client.controllers;

import java.io.IOException;
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
import nl.tudelft.oopp.api.models.LoginRequest;
import nl.tudelft.oopp.api.models.UserAuthResponse;


public class LoginSceneController {

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

            if (response != null) {
                if (response.getAlertType().equals("CONFIRMATION")) {

                    // Saves the user gotten from the UserAuthResponse.
                    HttpRequestHandler.saveUser(response.getUser());

                    // After that, show the confirmation message retrieved from the backend.
                    alertsController.show(
                            Alert.AlertType.CONFIRMATION,
                            response.getMessage()
                    );

                    // After that, go to the homepage.
                    goToHomepage();
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
     * Handles going the the homepage, without any event occurring.
     */
    public void goToHomepage() {
        try {
            Parent homepageParent = FXMLLoader.load(getClass().getResource("/mainScene.fxml"));
            Scene homepageScene = new Scene(homepageParent);
            Stage primaryStage = (Stage) (inputusername.getScene().getWindow());
            primaryStage.hide();
            primaryStage.setScene(homepageScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("IOException in ReservationsController");
        }
    }

    /**
     * Handles going to the registration page.
     *
     * @param event the event from where the function was called.
     */
    public void goToRegistration(MouseEvent event) {
        try {
            Parent registrationParent =
                    FXMLLoader.load(getClass().getResource("/registration.fxml"));
            Scene registrationScene = new Scene(registrationParent);
            registrationScene.getStylesheets()
                    .addAll(this.getClass().getResource("/registration.css").toExternalForm());
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            primaryStage.hide();
            primaryStage.setScene(registrationScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("IOException in LoginController");
        }
    }
}
