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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AdminRoomController implements Initializable {

    ObservableList list = FXCollections.observableArrayList();

    @FXML
    private ListView<String> todayRes;

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
        String a = "08:00";
        String b = "08:30";
        String c = "09:00";
        String d = "09:30";
        String e = "10:00";
        String f = "10:30";
        String g = "11:00";
        String h = "11:30";
        String i = "12:00";

        list.removeAll();
        list.addAll(a, b, c, d, e, f, g, h, i);
        todayRes.getItems().addAll(list);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
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
            System.out.println("IOException in AdminController");
        }
    }
}
