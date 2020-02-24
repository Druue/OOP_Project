package nl.tudelft.oopp.demo.views;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class QuoteDisplay extends Application {



    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/mainScene.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        Scene homepage = new Scene(root);

        primaryStage.setWidth(1000);
        primaryStage.setHeight(500);



        primaryStage.setTitle("Test");
        primaryStage.setScene(homepage);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
