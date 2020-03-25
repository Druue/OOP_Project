package nl.tudelft.oopp.client.controllers;

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

import java.io.IOException;

public class LoginSceneController {

    // the TextField object(s) from mainScene.fxml
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please provide a username and password.");
            alert.showAndWait();
        } else {
            LoginRequest loginRequest = new LoginRequest(username, password);
            UserAuthResponse response =
                    HttpRequestHandler.post("login", loginRequest, UserAuthResponse.class);

            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Response");
            alert.setHeaderText(null);
            if (response != null) {
                if (response.getAlertType().equals("CONFIRMATION")) {

                    // Saves the user gotten from the UserAuthResponse.
                    // This includes the user's id and details for a more personalized experience,
                    // and to make queries easier later on.
                    HttpRequestHandler.saveUser(response.getUser());

                    // Show an alert with the server response message.
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setContentText(response.getMessage());
                    alert.showAndWait();

                    // For now, goes back to the homepage.
                    goToHomepage();
                } else {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText(response.getMessage());
                    alert.showAndWait();
                }
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Invalid response from server.");
            }
        }
    }

    /**
     * Handles going back to the Homepage.
     *
     * @param event the event from where the function was called.
     */
    public void goToHomepage(ActionEvent event) {
        try {
            Parent homepageParent = FXMLLoader.load(getClass().getResource("/mainScene.fxml"));
            Scene homepageScene = new Scene(homepageParent);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            primaryStage.hide();
            primaryStage.setScene(homepageScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("IOException in ReservationsController");
        }
    }

    /**
     * >>>>>>>
     * master:client/src/main/java/nl/tudelft/oopp/demo/controllers/LoginSceneController.java
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
