package nl.tudelft.oopp.client.controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import nl.tudelft.oopp.client.MainApp;


public class AddBuildingsController {

    private static final Logger LOGGER = Logger.getLogger(AddBuildingsController.class.getName());
    private static final String BAD_RESOURCE_ERROR = "Faulty resource input at AddBuildingsController";

    @FXML
    public TextField buildingNameInput;
    public TextField buildingNumberInput;
    public TextField buildingOpeningTimeInput;
    public TextField buildingClosingTimeInput;
    public TextField buildingDescriptionInput;

    private static final HttpRequestHandler httpRequestHandler = new HttpRequestHandler();


    /**
     * Handles going to the admin homepage.
     */
    public void goToAdmin() {
        try {
            MainApp.goToPage("admin");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToAdmin()");
        }
    }


    /**
     * Handles going to the page for adding reservations.
     */
    public void goToRes() {
        try {
            MainApp.goToPage("reservations");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToRes()");
        }
    }

    /**
     * Handles going to the add rooms page for the admin.
     */
    public void goToAddRooms() {
        try {
            MainApp.goToPage("admin-addRoom");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToAddRooms()");
        }
    }

    /**
     * Handles going back to the login page.
     */
    public void goToLogIn() {
        try {
            MainApp.goToPage("login");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToRes()");
        }
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
                HttpRequestHandler.user.getUsername(),
                HttpRequestHandler.user.getUserKind(),
                requestBuilding
        );

        ServerResponseAlert response = httpRequestHandler.put(
                "buildings/admin/add",
                request,
                ServerResponseAlert.class
        );

        Alert alert;
        try {
            alert = new Alert(Alert.AlertType.valueOf(response.getAlertType()));
        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
        alert.setTitle("Response");
        alert.setHeaderText(null);
        alert.setContentText(response.getMessage());
        alert.showAndWait();
    }

}
