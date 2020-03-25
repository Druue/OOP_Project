package nl.tudelft.oopp.client.controllers;

import java.io.IOException;
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

public class RegistrationSceneController {
    @FXML
    public TextField registrationNameInput;
    public TextField registrationUsernameInput;
    public TextField registrationEmailInput;
    public PasswordField registrationPasswordInput;

    /**
     * Handles going to the login page.
     *
     * @param event the event from where the function was called.
     */
    public void goToLogin(MouseEvent event) {
        try {
            Parent loginParent = FXMLLoader.load(getClass().getResource("/login.fxml"));
            Scene loginScene = new Scene(loginParent);
            loginScene.getStylesheets()
                    .addAll(this.getClass().getResource("/login.css").toExternalForm());
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();


            primaryStage.hide();
            primaryStage.setScene(loginScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("IOException in ReservationsController");
        }
    }

    /**
     * Makes a request to the backend using the information that is present in the client's text
     * fields.
     */
    public void attemptRegistration() {

        // Get all text from text fields
        String username = registrationUsernameInput.getText();
        String password = registrationPasswordInput.getText();
        String email = registrationEmailInput.getText();
        String name = registrationNameInput.getText();

        // If any of these fields are empty: Send an alert.
        if (username.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all required fields.");
            alert.showAndWait();
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
                // Create an alert, and show it to the user.
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Invalid email address given.");
                alert.showAndWait();
                return;
            }

            User registrationRequest = new User(
                    new Details(name, null, null),
                    email, username, password, userKind);

            // Send a register request to the server.
            UserAuthResponse response = HttpRequestHandler.post("register", registrationRequest,
                    UserAuthResponse.class);

            // Create an alert, and show it to the user.
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Response");
            alert.setHeaderText(null);
            if (response != null) {
                if (response.getAlertType().equals("CONFIRMATION")) {
                    HttpRequestHandler.saveUser(response.getUser());
                    // HttpRequestHandler.user.setUserId(response.getUser().getUserId());
                    System.out.println(HttpRequestHandler.user.getId());
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
     */
    public void goToHomepage() {
        try {
            Parent homepageParent = FXMLLoader.load(getClass().getResource("/mainScene.fxml"));
            Scene homepageScene = new Scene(homepageParent);
            Stage primaryStage = (Stage) (registrationPasswordInput.getScene().getWindow());
            primaryStage.hide();
            primaryStage.setScene(homepageScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("IOException in RegistrationController");
        }
    }
}
