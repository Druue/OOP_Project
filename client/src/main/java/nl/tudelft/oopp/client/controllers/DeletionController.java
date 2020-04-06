package nl.tudelft.oopp.client.controllers;

import com.sun.jdi.request.InvalidRequestStateException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.api.models.ServerResponseAlert;
import nl.tudelft.oopp.client.AlertService;

public class DeletionController {

    HttpRequestHandler httpRequestHandler;

    @FXML
    TextField buildNumTextField;

    @FXML
    TextField roomNumTextField;

    /** This method parses an input from a {@link TextField} object into {@link Long} and then
     *      sends it as a request parameter as part of a post request for deletion of an item in
     *      the database. After successful parsing, the method creates a {@link ClientRequest}
     *      request object with the credentials of the {@link nl.tudelft.oopp.api.models.User} field
     *      in {@link HttpRequestHandler} and sends it to the server expecting a {@link ServerResponseAlert}
     *      object in the response body.
     * @param textField     The {@link TextField} object from which to get the number to send.
     * @param requestPath   The path of the request to create.
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

            AlertService.alert(Alert.AlertType.valueOf(response.getAlertType()),"Response",
                "Delete request send successfully. Successful deletion.");

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

    public void goToRes(ActionEvent actionEvent) {
        new AddBuildingsController().goToRes();
    }

    public void goToAddBuildings(ActionEvent actionEvent) {
        new AddBuildingsController().goToAddBuildings(actionEvent);
    }

    public void goToAddRooms(ActionEvent actionEvent) {
        new AddBuildingsController().goToAddRooms(actionEvent);
    }

    public void goToAdmin(ActionEvent actionEvent) {
        new AddBuildingsController().goToAdmin(actionEvent);
    }

    public void goToLogIn(ActionEvent actionEvent) {
        new AddBuildingsController().goToLogIn();
    }
}
