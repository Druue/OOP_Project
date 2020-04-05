package nl.tudelft.oopp.client.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
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


public class AddRoomsController {

    private static final HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

    // the TextField object from mainScene.fxml
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
        char[] buidlingIdNumber = buildingNumber.getText().toCharArray();
        for (char character : buidlingIdNumber) {
            if (!Character.isDigit(character)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("building number field must have numbers only");
                alert.showAndWait();
                return;
            }
        }
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

            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println("Reservable: " + new ObjectMapper().writeValueAsString(requestRoom));
            System.out.println("Request: " + new ObjectMapper().writeValueAsString(request));

            // Send the request and
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
            System.out.println("IOException in AddRoomsController");
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
                    (Stage) (roomNameInput.getScene().getWindow());

            primaryStage.hide();
            primaryStage.setScene(roomScene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("IOException in AddRoomsController");
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
                    (Stage) (roomNameInput.getScene().getWindow());

            primaryStage.hide();
            primaryStage.setScene(buildingScene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("IOException in AddRoomsController");
        }
    }
}
