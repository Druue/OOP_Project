package nl.tudelft.oopp.client.controllers;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
//import nl.tudelft.oopp.demo.apimodels.nl.tudelft.oopp.api.models.LoginDetails;
import nl.tudelft.oopp.api.models.LoginDetails;
import nl.tudelft.oopp.api.models.ServerResponse;
import nl.tudelft.oopp.client.communication.ServerCommunication;


public class LoginSceneController {

    // the TextField object from mainScene.fxml
    @FXML
    public TextField inputNetID;
    @FXML
    public TextField inputPassword;


    /**
     * Sends a login request to the backend, using the information stored in the text fields.
     */
    public void tryLogin() {

        String netID = inputNetID.getText();
        String password = inputPassword.getText();
        if (netID.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please provide a NetID and password.");
            alert.showAndWait();
        } else {
            LoginDetails loginDetails = new LoginDetails(netID, password);
            System.out.println(loginDetails.getNetID());
            //nl.tudelft.oopp.api.models.LoginDetails loginDetails = new nl.tudelft.oopp.api.models.LoginDetails(netID,password);
            JsonObject parameters = new JsonObject();
            parameters.addProperty("NetID", netID);
            parameters.addProperty("Password", password);
            Gson gson = new Gson();

            String response = gson.toJson(ServerCommunication.loginJson(loginDetails));
            System.out.println("Login Scene Response: " + response);
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Response");
            alert.setHeaderText(null);
            alert.setContentText(response);

            if (response.equals("CONFIRMATION")) {
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
