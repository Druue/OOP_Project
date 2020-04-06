package nl.tudelft.oopp.client.controllers;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


public class RoomEntryController {
    @FXML
    VBox roomEntryBackground;

    @FXML
    Label roomName;

    @FXML
    Label hasMultimedia;

    @FXML
    Label capacity;

    @FXML
    TextField startTimeInput;

    @FXML
    TextField endTimeInput;

    @FXML
    Button reserveButton;

    public RoomEntryController() { }

    public VBox getBackground() {
        return roomEntryBackground;
    }

    public Label getRoomName() {
        return roomName;
    }

    public Label hasMultimedia() {
        return hasMultimedia;
    }

    public Label getCapacity() {
        return capacity;
    }

    public TextField getStartTimeInput() {
        return startTimeInput;
    }

    public TextField getEndTimeInput() {
        return endTimeInput;
    }

    public Button getReserveButton() {
        return reserveButton;
    }
}

