package nl.tudelft.oopp.client.controllers;

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
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.api.models.Reservation;
import nl.tudelft.oopp.api.models.ReservationResponse;
import nl.tudelft.oopp.client.MainApp;


public class HomepageController<E> implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(HomepageController.class.getName());
    private static final String BAD_RESOURCE_ERROR = "Faulty resource input at HomePageController";

    private static final HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

    ObservableList<E> list = FXCollections.observableArrayList();

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

        /*
         * This block makes three rooms and tree reservations.
         */
        //        User exampleUser = new User(
        //                new Details(null, "first last", null, null),
        //                "example@mail.com",
        //                "flast",
        //                "badpass", UserKind.Student);
        //TODO: Add proper connection to backend.

        //        Room a = new Room("Example room a", false, false);
        //        Room b = new Room("Example room b", true, true);
        //        Room c = new Room("Example room c", true, false);

        //        Reservation reservationA = new Reservation((long) 0, exampleUser, a);
        //        Reservation reservationB = new Reservation((long) 1, exampleUser, b);
        //        Reservation reservationC = new Reservation((long) 2, exampleUser, c);
        //        List<Reservation> exampleReservationList = new ArrayList<>();
        //        exampleReservationList.add(reservationA);
        //        exampleReservationList.add(reservationB);
        //        exampleReservationList.add(reservationC);
        //
        //        for (Reservation s : exampleReservationList) {
        //            todayRes.getItems().add(s.getReservableId().getName());
        //        }

        // Set to true / remove the condition once the actual reservations controller is ready.
        boolean doRest = true;

        if (doRest) {

            List<Reservation> reservationList = new ArrayList<>();
            try {
                ReservationResponse response =
                    httpRequestHandler.get("reservations/admin/all", ReservationResponse.class);
                if (response != null) {
                    reservationList = response.getReservationList();
                    for (Reservation s : reservationList) {
                        todayRes.getItems().add("Room " + s.getReservable().getDetails().getName()
                            + " reserved by " + s.getUser().getName());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * Handles going to the mainScene.a
     *
     * @param event the scene from where the function was called.
     */
    public void goToMain(ActionEvent event) {
        try {
            Parent mainParent = FXMLLoader.load(getClass().getResource("/mainScene.fxml"));
            Scene mainScene = new Scene(mainParent);

            Stage primaryStage =
                (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            primaryStage.hide();
            primaryStage.setScene(mainScene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("IOException in HomepageController");
        }
    }

    /**
     * Handles going to the reservation page.
     *
     * @param event the scene from where the function was called.
     */
    public void goToRes(ActionEvent event) {
        try {
            Parent resParent = FXMLLoader.load(getClass().getResource("/reservations.fxml"));
            Scene resScene = new Scene(resParent);
            resScene.getStylesheets().addAll(this.getClass().getResource("/reservations.css").toExternalForm());

            Stage primaryStage = (Stage) (todayRes.getScene().getWindow());

            primaryStage.hide();
            primaryStage.setScene(resScene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("IOException in HomepageController");
        }
    }

    /**
     * Handles going back to the login page.
     *
     * @param event the scene from where the function was called.
     */
    public void goToLogIn() {
        try {
            MainApp.goToPage("login", "login");
        } catch (IOException e) {
            LOGGER.log(Level.FINE, BAD_RESOURCE_ERROR + ".goToRes()");
        }
    }
}
