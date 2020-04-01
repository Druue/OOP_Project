package nl.tudelft.oopp.client.controllers;

import javafx.scene.control.Alert;

public class AlertsController {

    private Alert alert;

    /**
     * Creates a new AlertsController. Used by Scene controllers to show alerts.
     */
    public AlertsController() {
        this.alert = new Alert(Alert.AlertType.NONE);
    }

    /**
     * Renews the {@link Alert} object to be new.
     * Used after showing an alert.
     */
    private void clear() {
        this.alert = new Alert(Alert.AlertType.NONE);
    }

    /**
     * Configures an {@link Alert} Alert based on the input, shows it, and renews the alert.
     * @param type The Alert type.
     * @param title The Alert title.
     * @param headerText The header text of the alert.
     * @param contentText The contents of the alert window.
     */
    public void show(Alert.AlertType type, String title, String headerText, String contentText) {

        // Set all alert properties correctly.
        this.alert.setAlertType(type);
        this.alert.setTitle(title);
        this.alert.setHeaderText(headerText);
        this.alert.setContentText(contentText);

        // Show the alert.
        this.alert.showAndWait();

        // Clear the alert contents.
        clear();
    }

    /**
     * Configures an {@link Alert} Alert based on the input.
     * This is used when less information needs to be shown.
     * @param type The Alert type.
     * @param contentText The contents of the alert.
     */
    public void show(Alert.AlertType type, String contentText) {

        // Set all alert properties correctly.
        this.alert.setAlertType(type);
        this.alert.setContentText(contentText);

        // Use default values.
        this.alert.setHeaderText(null);
        this.alert.setTitle("Response");

        // Show the alert
        this.alert.showAndWait();

        // Clear the alert contents.
        clear();
    }
}
