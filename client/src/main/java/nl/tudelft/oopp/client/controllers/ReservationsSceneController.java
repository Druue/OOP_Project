package nl.tudelft.oopp.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.Building;
import nl.tudelft.oopp.api.models.BuildingResponse;
import nl.tudelft.oopp.api.models.ServerResponseAlert;

public class ReservationsSceneController implements Initializable {

    public static final int MAX_DAYS_IN_ADVANCE = 14;
    public static final int RESPONSE_TIMEOUT = 5;

    @FXML
    ListView<Node> buildingsList;

    @FXML
    ChoiceBox<String> datesList;

    @FXML
    TextField buildingSearchField;

    @FXML
    TabPane roomsListWrapper;

    @FXML
    Tab roomsListTab;


    /**
     * Adds GUI that can only be generated at the moment of loading the page.
     * The parameter descriptions are from the official fxml javadoc.
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     *              the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateDatesChoiceBox();

        populateBuildingsScrollBox();

        //        roomsListWrapper.setVisible(false);
    }

    /**
     * Generates dates for the next 14(@MAX_DAYS_IN_ADVANCE) days and adds them to the GUI
     * in the dropdown menu.
     */
    private void populateDatesChoiceBox() {
        LocalDate date = LocalDate.now();

        String today = getDateString(date) + " (Today)";

        datesList.setValue(today);
        datesList.getItems().add(today);

        for (int i = 1; i != MAX_DAYS_IN_ADVANCE; i++) {
            datesList.getItems().add(getDateString(date.plusDays(i)));
        }
    }

    /**
     * Generates boxes for each building and adds them to the GUI.
     */
    private void populateBuildingsScrollBox() {
        BuildingResponse buildingResponse = HttpRequestHandler.get("buildings/all", BuildingResponse.class);

        DropShadow dropShadow = new DropShadow(BlurType.ONE_PASS_BOX, new Color(0,0,0,0.1), 2,4,2, 2);
        buildingSearchField.setEffect(dropShadow);

        List<Building> buildingList;
        if (waitForResponse(buildingResponse)) {
            buildingList = buildingResponse.getBuildingList(); //NullPointer handled in waitForResponse()
            List<Node> listOfEntries = new ArrayList<Node>();
            for (Building building : buildingList) {
                VBox buildingEntry = new VBox();
                buildingEntry.getStyleClass().add("buildingEntry");
                Label buildingName = new Label(building.getNumber() + "," + building.getName());
                buildingName.getStyleClass().add("buildingName");
                Label buildingOpeningTime = new Label("09:00 - 22:00 //hardcoded");
                buildingOpeningTime.getStyleClass().add("buildingOpeningTime");

                buildingEntry.getChildren().add(buildingName);
                buildingEntry.getChildren().add(buildingOpeningTime);
                buildingEntry.setPrefHeight(60);
                buildingEntry.prefWidthProperty().bind(buildingsList.widthProperty().subtract(50));
                //buildingEntry.setMaxWidth(Control.USE_PREF_SIZE);

                buildingEntry.setEffect(dropShadow);

                listOfEntries.add(buildingEntry);

                EventHandler<MouseEvent> mouseEventEventHandler = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            //FlowPane flowPane = FXMLLoader.load(getClass().getResource("/roomsList.fxml"));
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/roomsList.fxml"));
                            RoomsListController controller = new RoomsListController();
                            loader.setController(controller);
                            VBox tabContent = loader.load();
                            roomsListWrapper.setVisible(true);
                            roomsListTab.setContent(tabContent);

                            controller.generateInitialRooms(event);
                        } catch (IOException e) {
                            System.out.println("File Not Found");
                        }
                    }
                };
                buildingEntry.setOnMouseClicked(mouseEventEventHandler);
            }
            if (listOfEntries.size() != 0) {
                ObservableList<Node> observableListOfEntries = FXCollections.observableArrayList(listOfEntries);
                buildingsList.setItems(observableListOfEntries);
            }
        }
    }

    /**
     * Polls each second whether the buildingList was received by the BuildingResponse
     * until success or timeout.
     * @param buildingResponse The response object.
     * @return boolean whether a (non-null)response was received
     */
    private boolean waitForResponse(BuildingResponse buildingResponse) {
        int i = 0;
        while (i != RESPONSE_TIMEOUT) {
            if (buildingResponse.getBuildingList() != null) {
                return true;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                System.out.println("Problems with BuildingResponse in ReservationsSceneController.waitForResponse()");
                return false;
            }
            i++;
        }
        System.out.println("BuildingResponse timed out in ReservationsSceneController");
        return false;
    }

    /**
     * Generates a user friendly date string from a LocalDate.
     * @param date The {@link LocalDate} that needs to be transformed.
     * @return String shows day of month, month and day of week
     */
    private String getDateString(LocalDate date) {
        return date.getDayOfMonth() + " "
               + date.getMonth().name().substring(0,1)
               + date.getMonth().name().substring(1,3).toLowerCase()
               + " - "
               + date.getDayOfWeek().name().substring(0,1)
               + date.getDayOfWeek().name().substring(1).toLowerCase();
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

    public void addTestBuilding() {
        HttpRequestHandler.put("buildings/insert/new_building", null, ServerResponseAlert.class);
    }
}
