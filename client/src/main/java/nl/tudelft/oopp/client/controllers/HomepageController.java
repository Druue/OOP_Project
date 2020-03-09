package nl.tudelft.oopp.client.controllers;

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
import nl.tudelft.oopp.api.models.Reservation;
import nl.tudelft.oopp.api.models.ReservationResponse;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomepageController implements Initializable {

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

        List<Reservation> reservationList = HttpRequestHandler.get("reservations/all", ReservationResponse.class).getReservationList();

        for (Reservation s : reservationList) {
            todayRes.getItems().add(s.getReservationID().toString());
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

            Stage primaryStage =
                    (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            primaryStage.hide();
            primaryStage.setScene(resScene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("IOException in HomepageController");
        }
    }
}
