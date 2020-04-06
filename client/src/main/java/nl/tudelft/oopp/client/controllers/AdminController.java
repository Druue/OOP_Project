package nl.tudelft.oopp.client.controllers;

import com.sun.javafx.binding.Logging;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.api.models.Reservation;
import nl.tudelft.oopp.api.models.ReservationResponse;
import nl.tudelft.oopp.client.MainApp;


public class AdminController implements Initializable {

    private static final HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
    private static final Logger LOGGER = Logger.getLogger(AdminController.class.getName());
    private static final String BAD_RESOURCE_ERROR = "Faulty resource input at AdminController";

    @FXML
    private ListView<String> todayRes;
    @FXML
    private ListView<String> allRes;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTodayReservations();
        loadAllReservations();
    }

    /**
     * Handles loading all the reservations of all users to the ListView tab with all reservations in admin.fxml
     */
    private void loadAllReservations() {
        try {
            ClientRequest<String> userDetails = new ClientRequest<>(
                HttpRequestHandler.user.getUsername(),
                HttpRequestHandler.user.getUserKind(),
                null
            );
            List<Reservation> reservationList =
                httpRequestHandler.postList("reservations/admin/all", userDetails, Reservation.class);
            if (reservationList != null) {
                for (Reservation s : reservationList) {
                    allRes.getItems().add("Room " + s.getReservable().getDetails().getName() + " in "
                        + s.getReservable().getBuilding().getName() + " reserved from "
                        + ReservationsSceneController.hourAndMinutesString(s.getTimeslot().getStartTime()) + " to "
                        + ReservationsSceneController.hourAndMinutesString(s.getTimeslot().getEndTime()) + " on "
                        + s.getTimeslot().getStartTime().getDate() + "/" + (s.getTimeslot().getStartTime().getMonth() + 1)
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles loading the reservations of all users for current day to the ListView tab with
     * today's reservations in admin.fxml
     */
    private void loadTodayReservations() {
        try {
            ClientRequest<String> userDetails = new ClientRequest<>(
                HttpRequestHandler.user.getUsername(),
                HttpRequestHandler.user.getUserKind(),
                null
            );
            List<Reservation> reservationList =
                httpRequestHandler.postList("reservations/admin/current", userDetails, Reservation.class);
            if (reservationList != null) {
                for (Reservation s : reservationList) {
                    todayRes.getItems().add("Room " + s.getReservable().getDetails().getName() + "in"
                        + s.getReservable().getBuilding().getName() + " reserved from "
                        + ReservationsSceneController.hourAndMinutesString(s.getTimeslot().getStartTime()) + " to "
                        + ReservationsSceneController.hourAndMinutesString(s.getTimeslot().getEndTime())
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Handles going to the mainScene.
     */
    public void goToMain() {
        try {
            MainApp.goToPage("mainScene", null);
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
