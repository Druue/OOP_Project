package nl.tudelft.oopp.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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


public class AdminController implements Initializable {

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

    /**
     * Handles going to the room page for the admin.
     *
     * @param event the scene from where the function was called.
     */
    public void goToAdminRoom(ActionEvent event) {
        try {
            Parent roomParent = FXMLLoader.load(getClass().getResource("/admin-room.fxml"));
            Scene roomScene = new Scene(roomParent);

            Stage primaryStage =
                    (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            primaryStage.hide();
            primaryStage.setScene(roomScene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("IOException in AdminController");
        }
    }

    /**
     * Handles going to the add buildings page for the admin.
     *
     * @param event the scene from where the function was called.
     */
    public void goToAddBuildings(ActionEvent event) {
        try {
            Parent roomParent = FXMLLoader.load(getClass().getResource("/addBuildings.fxml"));
            Scene roomScene = new Scene(roomParent);

            Stage primaryStage =
                    (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            primaryStage.hide();
            primaryStage.setScene(roomScene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("IOException in AdminController");
        }
    }

    /**
     * Handles going to the add rooms page for the admin.
     *
     * @param event the scene from where the function was called.
     */
    public void goToAddRooms(ActionEvent event) {
        try {
            Parent roomParent = FXMLLoader.load(getClass().getResource("/addRooms.fxml"));
            Scene roomScene = new Scene(roomParent);

            Stage primaryStage =
                    (Stage) btn.getScene().getWindow();

            primaryStage.hide();
            primaryStage.setScene(roomScene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("IOException in AdminController");
        }
    }
}
