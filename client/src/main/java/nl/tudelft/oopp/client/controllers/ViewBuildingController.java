package nl.tudelft.oopp.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import nl.tudelft.oopp.client.MainApp;


public class ViewBuildingController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(ViewBuildingController.class.getName());
    private static final String BAD_RESOURCE_ERROR = "Faulty resource input at ViewBuilding";

    public Button btn;
    ObservableList list = FXCollections.observableArrayList();

    @FXML
    private ListView<String> todayRes;
    @FXML
    private ListView<String> allRes;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }

    /**
     * Handles loading the reserved rooms to the ListView item in homepage.fxml
     */
    private void loadData() {

        //        List<Reservation> reservationList = HttpRequestHandler.get("reservations/all",
        //        ReservationResponse.class).getReservationList();
        //
        //        for (Reservation s : reservationList) {
        //            todayRes.getItems().add(s.getReservationID().toString());
        //        }

    }

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
