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

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("A response");
        alert.setHeaderText(null);
        // Call the relay() function in Servercommunication.java with the user's input
        alert.setContentText(ServerCommunication.relay(userInputText));
        alert.showAndWait();
    }

    /**
     * Handles switching from one scene to another.
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
}
