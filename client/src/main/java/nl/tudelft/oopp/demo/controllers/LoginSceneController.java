package nl.tudelft.oopp.demo.controllers;

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
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginSceneController {

    // the TextField object from mainScene.fxml
    @FXML
    public TextField inputNetID;
    @FXML
    public TextField inputPassword;


    /**
     * Handles the Test POST Request.
     */
    public void tryLogin() throws JSONException {

        String netID = inputNetID.getText();
        String password = inputPassword.getText();
        if (netID.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please provide a NetID and password.");
            alert.showAndWait();
        } else {
            JSONObject parameters = new JSONObject();
            parameters.put("NetID", netID);
            parameters.put("Password", password);
            JSONObject response = ServerCommunication.login(parameters);

            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Response");
            alert.setHeaderText(null);
            alert.setContentText(response.getString("message"));

            if (response.getString("alertType").equals("CONFIRMATION")) {
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.showAndWait();
                //TODO: Add logic to go to new scene
                //For now, goes back to the homepage.
                goToHomepage();
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.showAndWait();
            }
        }
    }

    /**
     * Handles going back to the Homepage.
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
     * Handles going the the homepage, without any event occurring.
     */
    public void goToHomepage() {
        try {
            Parent homepageParent = FXMLLoader.load(getClass().getResource("/mainScene.fxml"));
            Scene homepageScene = new Scene(homepageParent);
            Stage primaryStage = (Stage) (inputNetID.getScene().getWindow());
            primaryStage.hide();
            primaryStage.setScene(homepageScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("IOException in ReservationsController");
        }
    }

    /**
     * Handles going to the registration page.
     * @param event the event from where the function was called.
     */
    public void goToRegistration(MouseEvent event) {
        try {
            Parent registrationParent = FXMLLoader.load(getClass().getResource("/registration.fxml"));
            Scene registrationScene = new Scene(registrationParent);
            registrationScene.getStylesheets().addAll(this.getClass().getResource("/registration.css").toExternalForm());
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            primaryStage.hide();
            primaryStage.setScene(registrationScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("IOException in LoginController");
        }
    }
}
