package nl.tudelft.oopp.client;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
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
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/mainScene.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        Scene homepage = new Scene(root);

        primaryStage.setTitle("Test");
        primaryStage.setScene(homepage);

        primaryStage.show();
    }
}
