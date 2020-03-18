package nl.tudelft.oopp.client.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.Building;
import nl.tudelft.oopp.api.models.BuildingResponse;


public class AddRoomsController {


    // the TextField object from mainScene.fxml
    @FXML
    public TextField roomNameInput;

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
            reservationsScene.getStylesheets().addAll(this.getClass().getResource("/reservations.css").toExternalForm());
            Stage primaryStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

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
            Parent homeParent = FXMLLoader.load(getClass().getResource("/mainScene.fxml"));
            Scene homeScene = new Scene(homeParent);

            Stage primaryStage =
                    (Stage) (roomNameInput.getScene().getWindow());

            primaryStage.hide();
            primaryStage.setScene(homeScene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("IOException in MainSceneController");
        }
    }

    /**
     * An example alert function, to showcase the use of the new API.
     */
    public void getBuildings() {

        // Make a standard alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("A response");
        alert.setHeaderText(null);

        //TODO: Add a proper connection to the backend.

        // Where the API shines: get a BuildingResponse object directly from the HttpRequestHandler
        BuildingResponse buildingResponse = HttpRequestHandler.get("getbuildings", BuildingResponse.class);

        // Add all of the building names into a string
        StringBuilder s = new StringBuilder("Building names: ");
        if (buildingResponse != null) {
            for (Building b : buildingResponse.getBuildingList()) {
                s.append(b.getName()).append(", ");
            }
        }

        // Show the alert with all the building names
        alert.setContentText(s.toString());
        alert.showAndWait();
    }

    /** Sends a request to the backend to add a Room to the database.
     *
     * @param event The event that called the function.
     */
    public void addRoom(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Room added.");
        alert.setHeaderText(null);
        //TODO: Add proper connection to the backend.

        //Room room = new Room(roomNameInput.getText(), true, false);
        //HttpRequestHandler.put("reservables/insert/new_room", null /* room */, ServerResponseAlert.class);

    }
}
