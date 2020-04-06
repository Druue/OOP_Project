package nl.tudelft.oopp.client;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the GUI of the application.
     *
     * @param primaryStage The container for the application (basically the window)
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        MainApp.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/login.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        Scene startScene = new Scene(root);

        primaryStage.setTitle("ReserveTUDelft");
        primaryStage.setScene(startScene);

        primaryStage.show();
    }

    /**
     * An app traversal method that loads a new scene based on the given fxml resource name (and optionally css).
     * @param fxmlName The name of the fxml file to load.
     * @throws IOException Thrown if the given resource address is not found.
     */
    public static void goToPage(String fxmlName) throws IOException {
        Parent parent = FXMLLoader.load(MainApp.class.getResource("/" + fxmlName + ".fxml"));
        primaryStage.setScene(new Scene(parent));
    }


    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
