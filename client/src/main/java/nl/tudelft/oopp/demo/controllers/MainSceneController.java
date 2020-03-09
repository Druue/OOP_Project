package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import org.json.JSONException;
import org.json.JSONObject;

public class MainSceneController {

    // the TextField object from mainScene.fxml
    @FXML
    public TextField userInput;

    /**
     * Handles clicking the 'Send a ping' button.
     */
    public void buttonPing() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("A response");
        alert.setHeaderText(null);
        alert.setContentText(ServerCommunication.ping());
        alert.showAndWait();
    }


    /**
     * Handles the Test POST Request.
     */
    public void testPostRequest() {

        // Get the text from the TextField
        String userInputText = userInput.getText();


        // Call the relay() function in Servercommunication.java with the user's input
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("A response");
            alert.setHeaderText(null);
            if (userInputText.isEmpty()) {
                alert.setContentText("Please provide some input.");
            } else {
                JSONObject json = ServerCommunication.relay(userInputText);
                alert.setContentText(json.toString());
            }
            alert.showAndWait();
        } catch (JSONException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("A server error has occurred.");
        }



    }

    /**
     * Handles going to the reservation page.
     * 
     * @param event the scene from where the function was called.
     */
    public void goToReservations(ActionEvent event) {
        try {
            Parent reservationsParent =
                    FXMLLoader.load(getClass().getResource("/reservations.fxml"));
            Scene reservationsScene = new Scene(reservationsParent);
            Stage primaryStage =
                    (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            primaryStage.hide();
            primaryStage.setScene(reservationsScene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("IOException in MainSceneController");
        }
    }

    /**
     * Handles going to the login page.
     *
     * @param event the scene from where the function was called.
     */
    public void goToLogin(ActionEvent event) {
        try {
            Parent loginParent = FXMLLoader.load(getClass().getResource("/login.fxml"));
            Scene loginScene = new Scene(loginParent);

            loginScene.getStylesheets()
                    .addAll(this.getClass().getResource("/login.css").toExternalForm());
            Stage primaryStage =
                    (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            primaryStage.hide();
            primaryStage.setScene(loginScene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("IOException in MainSceneController");
        }
    }

    /**
     * Handles going to the homepage.
     *
     * @param event the scene from where the function was called.
     */
    public void goToHome(ActionEvent event) {
        try {
            Parent homeParent = FXMLLoader.load(getClass().getResource("/homepage.fxml"));
            Scene homeScene = new Scene(homeParent);

            Stage primaryStage =
                    (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            primaryStage.hide();
            primaryStage.setScene(homeScene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("IOException in MainSceneController");
        }
    }
}
