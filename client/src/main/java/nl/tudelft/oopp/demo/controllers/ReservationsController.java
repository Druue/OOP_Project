package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;




public class ReservationsController {

    /**
     * Handles clicking the button.
     */
    public void buttonClicked() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Quote for you");
        alert.setHeaderText(null);
        alert.setContentText(ServerCommunication.getQuote());
        alert.showAndWait();
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
}
