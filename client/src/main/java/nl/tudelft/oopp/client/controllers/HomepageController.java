package nl.tudelft.oopp.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.api.models.Reservation;
import nl.tudelft.oopp.api.models.UserKind;
import nl.tudelft.oopp.client.MainApp;


public class HomepageController<E> implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(HomepageController.class.getName());
    private static final String BAD_RESOURCE_ERROR = "Faulty resource input at HomePageController";

    private static final HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

    @FXML
    private ListView<String> todayRes;
    @FXML
    private ListView<String> allRes;
    @FXML
    private ListView<String> futureRes;
    @FXML
    private TabPane viewReservations;
    @FXML
    private Button reserveButton;
    @FXML
    private Menu reserveMenu;
    @FXML
    private Button guestButton;
    @FXML
    private VBox guestInfo;
    @FXML
    private MenuButton menuButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!HttpRequestHandler.user.getUserKind().equals(UserKind.Guest)) {
            loadReservations(todayRes, "user/today");
            loadReservations(futureRes, "user/current");
            loadReservations(allRes, "user/all");
            guestButton.setVisible(false);
            reserveMenu.setText("Reserve a room");
        } else {
            viewReservations.setVisible(false);
            reserveButton.setVisible(false);
            menuButton.setVisible(false);
            reserveMenu.setText("View rooms");
            guestButton.setVisible(true);
            guestInfo.setVisible(true);
        }
    }

    /**
     * Populates the given listView with reservations taken from the mapping at ("reservations/" + path).
     * @param listView The ListView to display the reservations.
     * @param path The partial path, signifying from which mapping in the server ReservationsController to get the
     *             reservations from.
     */
    public static void loadReservations(ListView<String> listView, String path) {
        try {
            ClientRequest<String> userDetails = new ClientRequest<>(
                HttpRequestHandler.user.getUsername(),
                HttpRequestHandler.user.getUserKind(),
                null
            );
            List<Reservation> reservationList =
                httpRequestHandler.postList("reservations/" + path, userDetails, Reservation.class);
            if (reservationList != null) {
                for (Reservation r : reservationList) {
                    listView.getItems().add(generateReservationString(r, path));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String generateReservationString(Reservation reservation, String path) {
        String result = "Room " + reservation.getReservable().getDetails().getName() + " in "
            + reservation.getReservable().getBuilding().getName() + " reserved from "
            + ReservationsSceneController.hourAndMinutesString(reservation.getTimeslot().getStartTime()) + " to "
            + ReservationsSceneController.hourAndMinutesString(reservation.getTimeslot().getEndTime());

        if (path.contains("today")) {
            return result;
        } else {
            return result + " on " + reservation.getTimeslot().getStartTime().getDate()
                + " " + Month.of(reservation.getTimeslot().getStartTime().getMonth() + 1).toString();
        }

    }

    /**
     * Handles going to the page for adding reservations.
     */
    public void goToRes() {
        try {
            MainApp.goToPage("reservations");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToRes()");
        }
    }


    /**
     * Handles going back to the login page.
     */
    public void goToLogIn() {
        try {
            MainApp.goToPage("login");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToLogIn()");
        }
    }

    /**
     * Handles going to the copyrights
     */
    public void goToCopyrights() {
        try {
            MainApp.goToPage("copyrights");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToCopyrights()");
        }
    }
}
