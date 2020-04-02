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
        URL xmlUrl = getClass().getResource("/login.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        Scene startScene = new Scene(root);
        startScene.getStylesheets().addAll(this.getClass().getResource("/login.css").toExternalForm());

        primaryStage.setTitle("ReserveTUDelft");
        primaryStage.setScene(startScene);

        primaryStage.show();
    }
}
