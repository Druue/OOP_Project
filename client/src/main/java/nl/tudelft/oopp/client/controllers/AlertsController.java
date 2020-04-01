package nl.tudelft.oopp.client.controllers;

import javafx.scene.control.Alert;

public class AlertsController {

    private Alert alert;

    public AlertsController() {
        this.alert = new Alert(Alert.AlertType.NONE);
    }

    private void clear() {
        this.alert = new Alert(Alert.AlertType.NONE);
    }

    public void show(Alert.AlertType type, String title, String headerText, String contentText) {
        this.alert.setAlertType(type);
        this.alert.setTitle(title);
        this.alert.setHeaderText(headerText);
        this.alert.setContentText(contentText);

        this.alert.showAndWait();
        clear();
    }

    public void show(Alert.AlertType type, String contentText) {
        this.alert.setAlertType(type);
        this.alert.setContentText(contentText);
        this.alert.setHeaderText(null);
        this.alert.setTitle("Response");

        this.alert.showAndWait();
        clear();
    }
}
