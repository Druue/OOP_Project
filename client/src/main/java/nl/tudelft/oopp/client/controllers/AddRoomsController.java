package nl.tudelft.oopp.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.*;

import java.io.IOException;


public class AddRoomsController {


    // the TextField object from mainScene.fxml
    @FXML
    public TextField roomNameInput;
    public TextField roomCapacityInput;
    public CheckBox roomHasProjectorInput;
    public CheckBox roomForEmployee;
    public TextField roomDescriptionInput;
    public TextField roomIdInput;


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
                s.append(b.getDetails().getName()).append(", ");
            }
        }

        // Show the alert with all the building names
        alert.setContentText(s.toString());
        alert.showAndWait();
    }

    /**
     * Sends a request to the backend to add a Room to the database.
     *
     * @param event The event that called the function.
     */
    public void addRoom(ActionEvent event) {


        //TODO: Proper input validation.

        String name = roomNameInput.getText();
        Long id = Long.parseLong(roomIdInput.getText());
        int capacity = Integer.parseInt(roomCapacityInput.getText());
        boolean hasProjector = roomHasProjectorInput.isSelected();
        boolean forEmployee = roomForEmployee.isSelected();
        String description = roomDescriptionInput.getText();

        Room requestRoom = new Room(
                id,
                new Details(name, description, null),
                capacity,
                hasProjector,
                forEmployee
        );

        ClientRequest<Room> request = new ClientRequest<>(
                HttpRequestHandler.user.username,
                HttpRequestHandler.user.userKind,
                requestRoom
        );
        ServerResponseAlert response = HttpRequestHandler.put(
                "reservables/insert/new_room",
                request,
                ServerResponseAlert.class
        );
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Room added.");
        alert.setHeaderText(null);
        alert.setContentText(response.getMessage());
        alert.showAndWait();
    }

    /**
     * Handles going to the homepage.
     *
     * @param event the scene from where the function was called.
     */
    public void goToAdmin(ActionEvent event) {
        try {
            Parent homeParent = FXMLLoader.load(getClass().getResource("/admin.fxml"));
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
}
