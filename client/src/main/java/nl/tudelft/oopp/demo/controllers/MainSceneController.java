package nl.tudelft.oopp.demo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import nl.tudelft.oopp.demo.communication.ServerCommunication;

public class MainSceneController {

    // the TextField object from mainScene.fxml
    @FXML
    public TextField userInput;


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
     * Handles the Test POST Request
     */
    public void TestPostRequest() {

        // Get the text from the TextField
        String userInputText = userInput.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("A response");
        alert.setHeaderText(null);
        // Call the relay() function in Servercommunication.java with the user's input
        alert.setContentText(ServerCommunication.relay(userInputText));
        alert.showAndWait();
    }

}
