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
        startScene.getStylesheets().addAll(this.getClass().getResource("/login.css").toExternalForm());

        primaryStage.setTitle("ReserveTUDelft");
        primaryStage.setScene(startScene);

        primaryStage.show();
    }

    /**
     * An app traversal method that loads a new scene based on the given fxml resource name (and optionally css).
     * @param fxmlName The name of the fxml file to load.
     * @param cssName The name of the css file to add to the scene.
     * @throws IOException Thrown if the given resource address is not found.
     */
    public static void goToPage(String fxmlName, String cssName) throws IOException {
        Parent parent = FXMLLoader.load(MainApp.class.getResource("/" + fxmlName + ".fxml"));
        Scene scene = new Scene(parent);
        if (cssName != null && !cssName.equals("")) {
            scene.getStylesheets()
                .addAll(MainApp.class.getResource("/" + cssName + ".css").toExternalForm());
        }
        primaryStage.setScene(scene);
    }


    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
