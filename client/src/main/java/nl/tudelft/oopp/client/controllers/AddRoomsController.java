package nl.tudelft.oopp.client.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import nl.tudelft.oopp.api.models.Building;
import nl.tudelft.oopp.api.models.BuildingResponse;
import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.api.models.Details;
import nl.tudelft.oopp.api.models.Reservable;
import nl.tudelft.oopp.api.models.Room;
import nl.tudelft.oopp.api.models.ServerResponseAlert;
import nl.tudelft.oopp.client.MainApp;


public class AddRoomsController {

    private static final Logger LOGGER = Logger.getLogger(AddRoomsController.class.getName());
    private static final String BAD_RESOURCE_ERROR = "Faulty resource input at AddRoomsController";

    private static final HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

    @FXML
    public TextField roomNameInput;
    public TextField roomCapacityInput;
    public CheckBox roomHasProjectorInput;
    public CheckBox roomForEmployee;
    public TextField roomDescriptionInput;
    public TextField roomIdInput;
    public TextField buildingNumber;


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
     * Sends a request to the backend to add a Room to the database.
     *
     * @param event The event that called the function.
     */
    public void addRoom(ActionEvent event) {
        //validates the input for room capacity field
        char[] capactiyInput = roomCapacityInput.getText().toCharArray();
        for (char characters : capactiyInput) {
            if (!Character.isDigit(characters)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("capacity field must only have numbers");
                alert.showAndWait();
                return;
            }
        }
        //validates the input for roomId field
        char[] idInput = roomIdInput.getText().toCharArray();
        for (char character : idInput) {
            if (!Character.isDigit(character)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("id field must only have numbers");
                alert.showAndWait();
                return;
            }
        }
        //validates the input for buildingId field
        char[] buildingIdInput = buildingNumber.getText().toCharArray();
        for (char character : buildingIdInput) {
            if (!Character.isDigit(character)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("building id field must only have numbers");
                alert.showAndWait();
                return;
            }
        }
        String name = roomNameInput.getText();
        Long id = Long.parseLong(roomIdInput.getText());
        int capacity = Integer.parseInt(roomCapacityInput.getText());
        //checks if the capacity was between 5 and 20
        if (capacity < 5 || capacity > 20) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("capacity should be between 5-20");
            alert.showAndWait();
            return;
        }
        boolean hasProjector = roomHasProjectorInput.isSelected();
        boolean forEmployee = roomForEmployee.isSelected();
        String description = roomDescriptionInput.getText();
        long buildingId = Long.parseLong(buildingNumber.getText());

        Reservable requestRoom = new Room(
            id,
            new Details(name, description, null),
            capacity,
            hasProjector,
            forEmployee
        );

        ClientRequest<String> request;
        ServerResponseAlert response;

        try {

            // Construct the request containing a string representing json-serialized Reservable
            request = new ClientRequest<>(
                HttpRequestHandler.user.getUsername(),
                HttpRequestHandler.user.getUserKind(),
                new ObjectMapper().writeValueAsString(requestRoom)
            );

            // Send the request and get the response
            response = httpRequestHandler.put(
                "reservables/insert/room/" + buildingId,
                request,
                ServerResponseAlert.class
            );
            if (response.getMessage().equals("Building not found with this number")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText(response.getMessage());
                alert.showAndWait();
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Room added.");
            alert.setHeaderText(null);
            alert.setContentText(response.getMessage());
            alert.showAndWait();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

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
     * Handles going to the add buildings page for the admin.
     */
    public void goToAddBuildings() {
        try {
            MainApp.goToPage("admin-addBuilding");
        }  catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToAddBuildings()");
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
     * Handles going back to the login page.
     */
    public void goToLogIn() {
        try {
            MainApp.goToPage("login");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToRes()");
        }
    }
}
