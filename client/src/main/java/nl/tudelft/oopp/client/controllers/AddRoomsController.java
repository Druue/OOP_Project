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


        //TODO: Proper input validation.

        String name = roomNameInput.getText();
        Long id = Long.parseLong(roomIdInput.getText());
        int capacity = Integer.parseInt(roomCapacityInput.getText());
        boolean hasProjector = roomHasProjectorInput.isSelected();
        boolean forEmployee = roomForEmployee.isSelected();
        String description = roomDescriptionInput.getText();
        Long buildingId = Long.parseLong(buildingNumber.getText());

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

            // Send the request and get the response
            response = httpRequestHandler.put(
                "reservables/insert/room/" + buildingId,
                request,
                ServerResponseAlert.class
            );

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
    public void goToAddRooms(ActionEvent event) {
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
    public void goToAddBuildings(ActionEvent event) {
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

    /**
     * Handles going to the page for adding reservations.
     */
    public void goToRes() {
        try {
            Parent homeParent = FXMLLoader.load(getClass().getResource("/reservations.fxml"));
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
     * Handles going back to the login page.
     *
     * @param event the scene from where the function was called.
     */
    public void goToLogIn() {
        try {
            MainApp.goToPage("login", "login");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToRes()");
        }
    }
}
