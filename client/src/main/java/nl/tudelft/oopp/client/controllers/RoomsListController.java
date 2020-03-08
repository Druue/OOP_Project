package nl.tudelft.oopp.client.controllers;

import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import nl.tudelft.oopp.api.models.Building;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomsListController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * This will get all the rooms of the selected building and create GUI for them,
     * as well as the initial tab layout
     * @param event Through this, we can know which building is selected
     */
    public static void generateInitialRooms(MouseEvent event) {
        Building sourceBuilding = (Building) event.getSource();
        System.out.println("Alles goed!");



    }


}
