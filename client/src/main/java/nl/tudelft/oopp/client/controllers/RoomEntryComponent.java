package nl.tudelft.oopp.client.controllers;

import java.io.IOException;
import javafx.fxml.FXMLLoader;


public class RoomEntryComponent {

    // TODO: Add a better javadoc comment.
    /**
     * Initialises a new instance of {@link RoomEntryComponent}.
     */
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
