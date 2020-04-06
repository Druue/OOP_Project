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
     * Entry function for the client, that gets called on hitting submit.
     */
    public void getBuildings() {

        // Make a standard alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("A response");
        alert.setHeaderText(null);

        //TODO: Add a proper connection to the backend

        // Where the API shines: get a BuildingResponse object directly from the HttpRequestHandler
        BuildingResponse buildingResponse = httpRequestHandler.get("getbuildings",
                BuildingResponse.class);

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
        //validates the input for the building number
        char[] number = buildingNumberInput.getText().toCharArray();
        boolean test = false;
        for (char characters : number) {
            if (!Character.isDigit(characters)) {
                test = true;
            }
        }
        if (test) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("building number field must have ONLY numbers");
            alert.showAndWait();
            return;
        }
        //validates the regex of the opening hour time
        boolean regex = true;
        if (!buildingOpeningTimeInput.getText().matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            regex = false;
        }
        if (!regex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("opening time input field should have this regex eg.06:30");
            alert.showAndWait();
            return;
        }
        //validates the input for closing hour time
        boolean regex2 = true;
        if (!buildingClosingTimeInput.getText().matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            regex2 = false;
        }
        if (!regex2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("closing time input field should have this regex eg. 06:30");
            alert.showAndWait();
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
