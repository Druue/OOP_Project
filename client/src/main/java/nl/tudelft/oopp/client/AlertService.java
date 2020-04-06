package nl.tudelft.oopp.client;

import javafx.scene.control.Alert;

public class AlertService {

    /** Creates an {@link Alert} object with the provided data and displays it.
     * @param alertType     The type of the alert.
     * @param title         The title of the alert.
     * @param text          The message of the alert.
     */
    public static void alert(Alert.AlertType alertType, String title, String text) {
        Alert alert = new Alert(alertType, text);
        alert.setTitle(title);
        alert.showAndWait();
    }

    public static void alertConfirmation(String title, String text) {
        alert(Alert.AlertType.CONFIRMATION, title, text);
    }

    public static void alertInformation(String title, String text) {
        alert(Alert.AlertType.INFORMATION, title, text);
    }

    public static void alertWarning(String title, String text) {
        alert(Alert.AlertType.WARNING, title, text);
    }

    public static void alertError(String title, String text) {
        alert(Alert.AlertType.ERROR, title, text);
    }
}
