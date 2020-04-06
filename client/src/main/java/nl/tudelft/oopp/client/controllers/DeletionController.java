package nl.tudelft.oopp.client.controllers;

import com.sun.jdi.request.InvalidRequestStateException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.api.models.ServerResponseAlert;
import nl.tudelft.oopp.client.AlertService;
import nl.tudelft.oopp.client.MainApp;

public class DeletionController {

    private static final Logger LOGGER = Logger.getLogger(DeletionController.class.getName());
    private static final String BAD_RESOURCE_ERROR = "Faulty resource input at DeletionController";

    HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

    @FXML
    TextField buildNumTextField;

    @FXML
    TextField roomNumTextField;

    /**
     * This method parses an input from a {@link TextField} object into {@link Long} and then
     * sends it as a request parameter as part of a post request for deletion of an item in
     * the database. After successful parsing, the method creates a {@link ClientRequest}
     * request object with the credentials of the {@link nl.tudelft.oopp.api.models.User} field
     * in {@link HttpRequestHandler} and sends it to the server expecting a {@link ServerResponseAlert}
     * object in the response body.
     *
     * @param textField   The {@link TextField} object from which to get the number to send.
     * @param requestPath The path of the request to create.
     */
    public void deleteItem(TextField textField, String requestPath) {

        long parameter;

        // Parse the input number or alert for parsing error
        try {
            parameter = Long.parseLong(textField.getText());
            requestPath = requestPath + parameter;
        } catch (NumberFormatException e) {
            AlertService.alertError("ERROR", "Invalid input: Cannot be interpreted as a number!");
            return;
        }

        // Create a request
        ClientRequest<String> deleteRequest = new ClientRequest<>(
            HttpRequestHandler.user.getUsername(),
            HttpRequestHandler.user.getUserKind(),
            null
        );


        // Send the request and get the response
        try {
            ServerResponseAlert response = httpRequestHandler.delete(
                requestPath,
                deleteRequest,
                ServerResponseAlert.class);

            AlertService.alert(Alert.AlertType.valueOf(response.getAlertType()), "Response",
                "Server response: " + response.getMessage());

        } catch (InvalidRequestStateException e) {
            AlertService.alertError("Request error",
                "The request could not be sent or the server failed!");
        }

    }


    public void deleteBuilding() {
        deleteItem(buildNumTextField, "/buildings/admin/delete/?number=");
    }

    public void deleteRoom() {
        deleteItem(roomNumTextField, "reservables/admin/delete/?number=");
    }

    /**
     * Handles going to the room page for the admin.
     */
    public void goToAdminRoom() {
        try {
            MainApp.goToPage("admin-room");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToAdminRoom()");
        }
    }


    /**
     * Handles going to the add buildings page for the admin.
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
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToAddBuildings()");
        }
    }

    /**
     * Handles going to the delete buildings page for the admin.
     */
    public void goToDeleteBuildings() {
        try {
            MainApp.goToPage("admin-deleteBuilding");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToDeleteBuildings()");
        }
    }

    /**
     * Handles going to the view buildings page for the admin.
     */
    public void goToBuildings() {
        try {
            MainApp.goToPage("admin-viewBuilding");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToBuildings()");
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
     * Handles going to the delete buildings page for the admin.
     */
    public void goToDeleteRooms() {
        try {
            MainApp.goToPage("admin-deleteRoom");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToDeleteRooms()");
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
