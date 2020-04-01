package nl.tudelft.oopp.client.controllers;

import java.io.IOException;
import java.sql.Timestamp;
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
import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.api.models.Details;
import nl.tudelft.oopp.api.models.ServerResponseAlert;
import nl.tudelft.oopp.api.models.TimeSlot;


public class AddBuildingsController {


    // the TextField object from mainScene.fxml
    @FXML
    public TextField buildingNameInput;
    public TextField buildingNumberInput;
    public TextField buildingOpeningTimeInput;
    public TextField buildingClosingTimeInput;
    public TextField buildingDescriptionInput;

    public HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

    public AlertsController alertsController;


    public AddBuildingsController() {
        alertsController = new AlertsController();
    }

    public AddBuildingsController(AlertsController alertsController) {
        this.alertsController = alertsController;
    }

    /**
     * Handles going to the homepage.
     *
     */
    public void goToAdmin() {
        try {
            Parent homeParent = FXMLLoader.load(getClass().getResource("/admin.fxml"));
            Scene homeScene = new Scene(homeParent);

            Stage primaryStage =
                    (Stage) (buildingNameInput.getScene().getWindow());

            primaryStage.hide();
            primaryStage.setScene(homeScene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("IOException in MainSceneController");
        }
    }

    /**
     * Entry function for the client, that gets called on hitting submit.
     */
    public void addBuildingEntry() {

        //TODO: Input validation.

        String buildingName = buildingNameInput.getText();
        Long buildingNumber = Long.parseLong(buildingNumberInput.getText());
        String buildingDescription = buildingDescriptionInput.getText();
        int openingHour = Integer.parseInt(buildingOpeningTimeInput.getText());
        int closingHour = Integer.parseInt(buildingClosingTimeInput.getText());

        addBuilding(buildingName, buildingNumber, buildingDescription, openingHour, closingHour);

    }

    /**
     * Sends a request to the backend to add a Building to the database.
     */
    public void addBuilding(String buildingName, Long buildingNumber, String buildingDescription,
            int openingHour, int closingHour) {

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
                HttpRequestHandler.user.getUsername(),
                HttpRequestHandler.user.getUserKind(),
                requestBuilding
        );

        ServerResponseAlert response = httpRequestHandler.put(
                "buildings/admin/add",
                request,
                ServerResponseAlert.class
        );

        alertsController.show(
                Alert.AlertType.INFORMATION,
                response.getMessage()
        );
    }
}
