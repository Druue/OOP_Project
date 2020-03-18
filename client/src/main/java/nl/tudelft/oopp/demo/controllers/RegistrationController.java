package nl.tudelft.oopp.demo.controllers;

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
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationController {
    @FXML
    public TextField registrationNameInput;
    public TextField registrationNetIdInput;
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
    public void attemptRegistration() throws JSONException {

        // Get all text from text fields
        String netID = registrationNetIdInput.getText();
        String password = registrationPasswordInput.getText();
        String name = registrationNameInput.getText();
        String email = registrationEmailInput.getText();

        // If any of these fields are empty: Send an alert.
        if (netID.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all required fields.");
            alert.showAndWait();
        } else {

            // Create a JSON Object containing the user's registration details.
            JSONObject parameters = new JSONObject();
            parameters.put("NetID", netID);
            parameters.put("Password", password);
            parameters.put("name", name);
            parameters.put("email", email);

            // Send a register request to the server.
            JSONObject response = ServerCommunication.register(parameters);

            // Create an alert, and show it to the user.
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Response");
            alert.setHeaderText(null);
            alert.setContentText(response.getString("message"));

            if (response.getString("alertType").equals("CONFIRMATION")) {
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.showAndWait();
                // TODO: Add logic to go to new scene
                // For now, goes back to the homepage.
                goToHomepage();
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.showAndWait();
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
