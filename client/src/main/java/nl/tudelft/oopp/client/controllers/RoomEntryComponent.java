package nl.tudelft.oopp.client.controllers;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class RoomEntryComponent {
    public RoomEntryComponent() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/roomEntry.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("IOException with loading RoomEntry");
        }
    }
}
