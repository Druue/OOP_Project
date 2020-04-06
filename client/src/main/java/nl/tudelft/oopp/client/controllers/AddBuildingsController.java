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
import nl.tudelft.oopp.client.AlertService;
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
        //validates the input for the building number
        char[] number = buildingNumberInput.getText().toCharArray();
        boolean test = false;
        for (char characters : number) {
            if (!Character.isDigit(characters)) {
                test = true;
            }
        }
        if (test) {
            AlertService.alertError("ERROR", "Building number field must have ONLY numbers!");
            return;
        }
        //validates the regex of the opening hour time
        boolean regex = true;
        if (!buildingOpeningTimeInput.getText().matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            regex = false;
        }
        if (!regex) {
            AlertService.alertError("ERROR", "Opening time input field should have this regex eg.06:30 !");
            return;
        }
        //validates the input for closing hour time
        boolean regex2 = true;
        if (!buildingClosingTimeInput.getText().matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            regex2 = false;
        }
        if (!regex2) {
            AlertService.alertError("ERROR", "Closing time input field should have this regex eg. 06:30 !");
            return;
        }
        int openingHour = 0;
        int openingHourMinutes = 0;
        int closingHour = 0;
        int closingHourMinutes = 0;
        char[] openingInputField = buildingOpeningTimeInput.getText().toCharArray();
        char[] closingInputField = buildingClosingTimeInput.getText().toCharArray();

        for (int i = 0; i < openingInputField.length; i++) {
            String hour = openingInputField[0] + Character.toString(openingInputField[1]);
            openingHour = Integer.parseInt(hour);
            String string = Character.toString(openingInputField[i]);
            if (string.equals(":")) {
                String minutes = openingInputField[i + 1] + Character.toString(openingInputField[i + 2]);
                openingHourMinutes = Integer.parseInt(minutes);
            }
        }
        for (int i = 0; i < closingInputField.length; i++) {
            String hour = closingInputField[0] + Character.toString(closingInputField[1]);
            closingHour = Integer.parseInt(hour);
            String string = Character.toString(closingInputField[i]);
            if (string.equals(":")) {
                String minutes = closingInputField[i + 1] + Character.toString(closingInputField[i + 2]);
                closingHourMinutes = Integer.parseInt(minutes);
            }
        }
        String buildingName = buildingNameInput.getText();
        Long buildingNumber = Long.parseLong(buildingNumberInput.getText());
        String buildingDescription = buildingDescriptionInput.getText();

        Timestamp openingTime = new Timestamp(
                0, 0, 0,
                openingHour, openingHourMinutes, 0, 0
        );
        Timestamp closingTime = new Timestamp(
                0, 0, 0,
                closingHour, closingHourMinutes, 0, 0
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


        try {
            AlertService.alert(Alert.AlertType.valueOf(
                response.getAlertType()),
                "Response",
                response.getMessage());
        } catch (Exception e) {
            AlertService.alertInformation("Response", response.getMessage());
        }
    }

}
