package nl.tudelft.oopp.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.Building;
import nl.tudelft.oopp.api.models.BuildingResponse;


public class ReservationsSceneController implements Initializable {

    public static final int MAX_DAYS_IN_ADVANCE = 14;

    @FXML
    VBox buildingsList;

    @FXML
    ChoiceBox<String> datesList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Populate dates choice
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd");
//        LocalDate localDate = LocalDate.now();
//        System.out.println(dateTimeFormatter.format(localDate));

        LocalDate date = LocalDate.now();

        String today = getDateString(date) + " (Today)";

        datesList.setValue(today);
        datesList.getItems().add(today);

        for(int i = 1; i != MAX_DAYS_IN_ADVANCE; i++) {
            datesList.getItems().add(getDateString(date.plusDays(i)));
        }


        // Populate buildings scroll box
        BuildingResponse buildingResponse = HttpRequestHandler.get("getbuildings", BuildingResponse.class);

        for(Building building : buildingResponse.getBuildingList()) {
//            VBox buildingEntry = new VBox();
//            buildingEntry.getStyleClass().add("buildingEntry");
            VBox buildingEntry = new VBox();
            buildingEntry.getStyleClass().add("buildingEntry");
            Label buildingName = new Label(building.getBuildingInt() + "," + building.getName());
            buildingName.getStyleClass().add("buildingName");
            Label buildingOpeningTime = new Label("09:00 - 22:00 //hardcoded");
            buildingOpeningTime.getStyleClass().add("buildingOpeningTime");

            buildingEntry.getChildren().add(buildingName);
            buildingEntry.getChildren().add(buildingOpeningTime);
            buildingEntry.setPrefSize(300,60);

            DropShadow dropShadow = new DropShadow();
            buildingEntry.setEffect(dropShadow);

            buildingsList.getChildren().add(buildingEntry);
        }
    }

    private String getDateString(LocalDate date) {
        return date.getDayOfMonth() + " " +
                date.getMonth().name().substring(0,1) +
                date.getMonth().name().substring(1,3).toLowerCase();
    }

    /**
     * Handles going back to the Homepage.
     * @param event the event from where the function was called.
     */
    public void goToHomepage(ActionEvent event) {
        try {
            Parent homepageParent = FXMLLoader.load(getClass().getResource("/mainScene.fxml"));
            Scene homepageScene = new Scene(homepageParent);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            primaryStage.hide();
            primaryStage.setScene(homepageScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("IOException in ReservationsController");
        }
    }
}
