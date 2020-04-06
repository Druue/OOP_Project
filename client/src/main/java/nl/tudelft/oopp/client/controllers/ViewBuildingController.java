package nl.tudelft.oopp.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.BuildingBasicInfo;
import nl.tudelft.oopp.client.MainApp;


public class ViewBuildingController /*implements Initializable*/ {

    private static final HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

    private static final Logger LOGGER = Logger.getLogger(ViewBuildingController.class.getName());
    private static final String BAD_RESOURCE_ERROR = "Faulty resource input at ViewBuilding";

//    public static final int MAX_DAYS_IN_ADVANCE = 14;
//    public static final int RESPONSE_TIMEOUT = 5;
//
//    @FXML
//    ListView<Node> buildingsList;
//
//    @FXML
//    ChoiceBox<LocalDate> datesList;
//
//    @FXML
//    TextField buildingSearchField;
//
//    @FXML
//    TabPane roomsListWrapper;
//
//    @FXML
//    Tab roomsListTab;
//
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        populateBuildingsScrollBox();
//    }
//
//    /**
//     * Generates boxes for each building and adds them to the GUI.
//     */
//    private void populateBuildingsScrollBox() {
//        List<BuildingBasicInfo> buildingBasicInfos = httpRequestHandler.getList("buildings/user/all/information",
//                BuildingBasicInfo.class);
//
//        DropShadow dropShadow = new DropShadow(BlurType.ONE_PASS_BOX, new Color(0,0,0,0.1), 2,4,2, 2);
//        buildingSearchField.setEffect(dropShadow);
//
//        if (waitForResponse(buildingBasicInfos)) {
//            List<Node> listOfEntries = new ArrayList<Node>();
//            for (BuildingBasicInfo building : buildingBasicInfos) {
//                VBox buildingEntry = new VBox();
//                buildingEntry.getStyleClass().add("buildingEntry");
//                Label buildingName = new Label(building.getNumber() + "," + building.getDetails().getName());
//                buildingName.getStyleClass().add("buildingName");
//                Label buildingOpeningTime = new Label(hourAndMinutesString(building.getOpeningHours().getStartTime())
//                        + " - " + hourAndMinutesString(building.getOpeningHours().getEndTime()));
//                buildingOpeningTime.getStyleClass().add("buildingOpeningTime");
//
//                buildingEntry.getChildren().add(buildingName);
//                buildingEntry.getChildren().add(buildingOpeningTime);
//                buildingEntry.setPrefHeight(60);
//                buildingEntry.prefWidthProperty().bind(buildingsList.widthProperty().subtract(50));
//                //buildingEntry.setMaxWidth(Control.USE_PREF_SIZE);
//
//                buildingEntry.setEffect(dropShadow);
//
//                listOfEntries.add(buildingEntry);
//
//                EventHandler<MouseEvent> mouseEventEventHandler = new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent event) {
//                        try {
//                            //FlowPane flowPane = FXMLLoader.load(getClass().getResource("/roomsList.fxml"));
//                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/roomsList.fxml"));
//                            RoomsListController controller = new RoomsListController();
//                            loader.setController(controller);
//                            VBox tabContent = loader.load();
//                            roomsListWrapper.setVisible(true);
//                            roomsListTab.setContent(tabContent);
//
//                            controller.initialize(building);
//                        } catch (IOException e) {
//                            System.out.println("File Not Found");
//                        }
//                    }
//                };
//                buildingEntry.setOnMouseClicked(mouseEventEventHandler);
//            }
//            if (listOfEntries.size() != 0) {
//                ObservableList<Node> observableListOfEntries = FXCollections.observableArrayList(listOfEntries);
//                buildingsList.setItems(observableListOfEntries);
//            }
//        }
//    }
//
//    /**
//     * Polls each second whether the buildingList was received by the BuildingResponse
//     * until success or timeout.
//     * @param buildings The response object.
//     * @return boolean whether a (non-null)response was received
//     */
//    private boolean waitForResponse(List<BuildingBasicInfo> buildings) {
//        int i = 0;
//        while (i != RESPONSE_TIMEOUT) {
//            if (buildings != null) {
//                return true;
//            }
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (Exception e) {
//                System.out.println("Problems with getting BuildingsDetails in "
//                        + "ReservationsSceneController.waitForResponse()");
//                return false;
//            }
//            i++;
//        }
//        System.out.println("ReservationsSceneController: Getting BuildingsDetails timed out or there are no buildings");
//        return false;
//    }
//
//    /**
//     * Gets the hour and minutes from a {@link Timestamp} in the format (H)H:MM.
//     * @param timestamp The timestamp used.
//     * @return a String of format (H)H:MM (e.g. 9:13 or 12:00).
//     */
//    public static String hourAndMinutesString(Timestamp timestamp) {
//        String result = timestamp.getHours() + ":";
//        if (timestamp.getMinutes() < 10) {
//            return result + "0" + timestamp.getMinutes();
//        } else {
//            return result + timestamp.getMinutes();
//        }
//    }

    /**
     * Handles going to the mainScene.
     */
    public void goToAdmin() {
        try {
            MainApp.goToPage("admin", null);
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToMain()");
        }
    }

    /**
     * Handles going to the room page for the admin.
     */
    public void goToAdminRoom() {
        try {
            MainApp.goToPage("admin-room", null);
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToAdminRoom()");
        }
    }

    /**
     * Handles going to the add buildings page for the admin.
     */
    public void goToAddBuildings() {
        try {
            MainApp.goToPage("admin-addBuilding", null);
        }  catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToAddBuildings()");
        }
    }

    /**
     * Handles going to the add rooms page for the admin.
     */
    public void goToAddRooms() {
        try {
            MainApp.goToPage("admin-addRoom", null);
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToAddRooms()");
        }
    }

    /**
     * Handles going to the page for adding reservations.
     */
    public void goToRes() {
        try {
            MainApp.goToPage("reservations", "reservations");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToRes()");
        }
    }

    /**
     * Handles going back to the login page.
     */
    public void goToLogIn() {
        try {
            MainApp.goToPage("login", "login");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToRes()");
        }
    }
}
