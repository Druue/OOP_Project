package nl.tudelft.oopp.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.*;

import java.io.IOException;
import java.sql.Timestamp;


public class AddBuildingsController {


    // the TextField object from mainScene.fxml
    @FXML
    public TextField buildingNameInput;
    public TextField buildingNumberInput;
    public TextField buildingOpeningTimeInput;
    public TextField buildingClosingTimeInput;
    public TextField buildingDescriptionInput;


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
                    (Stage) (buildingNameInput.getScene().getWindow());

            primaryStage.hide();
            primaryStage.setScene(homeScene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("IOException in AddBuildingsController");
        }
    }

    /**
     * Handles going to the add rooms page.
     *
     * @param event the scene from where the function was called.
     */
    public void goToAddRoom(ActionEvent event) {
        try {
            Parent roomParent = FXMLLoader.load(getClass().getResource("/admin-addRoom.fxml"));
            Scene roomScene = new Scene(roomParent);

            Stage primaryStage =
                    (Stage) (buildingNameInput.getScene().getWindow());

            primaryStage.hide();
            primaryStage.setScene(roomScene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("IOException in AddBuildingsController");
        }
    }

    /**
     * Handles going to the add buildings page.
     *
     * @param event the scene from where the function was called.
     */
    public void goToAddBuilding(ActionEvent event) {
        try {
            Parent buildingParent = FXMLLoader.load(getClass().getResource("/admin-addBuilding.fxml"));
            Scene buildingScene = new Scene(buildingParent);

            Stage primaryStage =
                    (Stage) (buildingNameInput.getScene().getWindow());

            primaryStage.hide();
            primaryStage.setScene(buildingScene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("IOException in AddBuildingsController");
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
                s.append(b.getDetails().getName()).append(", ");
            }
        }

        // Show the alert with all the building names
        alert.setContentText(s.toString());
        alert.showAndWait();
    }

    /**
     * Sends a request to the backend to add a Building to the database.
     */
    public void addBuilding() {

        //TODO: Input validation.

        String buildingName = buildingNameInput.getText();
        Long buildingNumber = Long.parseLong(buildingNumberInput.getText());
        String buildingDescription = buildingDescriptionInput.getText();
        int openingHour = Integer.parseInt(buildingOpeningTimeInput.getText());
        int closingHour = Integer.parseInt(buildingClosingTimeInput.getText());

        Timestamp openingTime = new Timestamp(
                0, 0, 0,
                openingHour, 0, 0, 0
        );
        Timestamp closingTime = new Timestamp(
                0, 0, 0,
                closingHour, 0, 0, 0
        );

        Building requestBuilding = new Building(
                buildingNumber,
                new Details(
                        buildingName, buildingDescription, null
                ),
                new TimeSlot(openingTime, closingTime)
        );

        ClientRequest<Building> request = new ClientRequest<>(
                HttpRequestHandler.user.username,
                HttpRequestHandler.user.userKind,
                requestBuilding
        );

        ServerResponseAlert response = HttpRequestHandler.put(
                "buildings/admin/add",
                request,
                ServerResponseAlert.class
        );

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Response");
        alert.setHeaderText(null);
        alert.setContentText(response.getMessage());
        alert.showAndWait();
    }
}
