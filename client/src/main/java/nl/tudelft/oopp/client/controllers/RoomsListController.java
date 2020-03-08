package nl.tudelft.oopp.client.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import nl.tudelft.oopp.api.models.Building;


public class RoomsListController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * This will get all the rooms of the selected building and create GUI for them,
     * as well as the initial tab layout.
     * @param event The event that called the function.
     *              Used to find out what Building the request was generated from.
     */
    public static void generateInitialRooms(MouseEvent event) {
        Building sourceBuilding = (Building) event.getSource();
        System.out.println("Alles goed!");



    }


}
