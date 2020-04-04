package nl.tudelft.oopp.client.controllers;

import com.sun.tools.javac.Main;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.api.models.Reservation;
import nl.tudelft.oopp.api.models.Room;
import nl.tudelft.oopp.api.models.ServerResponseAlert;
import nl.tudelft.oopp.api.models.TimeSlot;
import nl.tudelft.oopp.client.MainApp;


public class RoomEntryComponent extends Pane {

    private static final HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

    RoomEntryController controller;
    Room room;

    /**
     * Creates a new RoomEntryComponent.
     */
    public RoomEntryComponent(Room room) {
        super();
        this.room = room;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/roomEntry.fxml"));
            controller = new RoomEntryController();

            loader.setController(controller);

            final Pane roomEntry = loader.load();

            setRoomDetails(room);

            //controller.generateTimeline(controller.getStartTimeInput().getText(), controller.getEndTimeInput().getText());

            controller.getReserveButton().setOnAction(
                (ActionEvent event) -> {
                    reserveRoom();
                }
            );

            this.getChildren().add(roomEntry);

        } catch (IOException e) {
            System.out.println("Error loading room entry");
        }


    }

    private void setRoomDetails(Room room) {
        if (room.getDetails() != null) {
            getRoomName().setText(room.getDetails().getName());
        }
        getCapacity().setText(room.getCapacity() + "");
        if (!room.isHasProjector()) {
            getHasMultimedia().setText("No multimedia");
        }
    }

    /**
     * Sends a post request with the {@link Room} connected to this RoomEntry and the
     * input from startTimeInput and endTimeInput text fields.
     */
    public void reserveRoom() {
        if (!getStartTimeInput().getText().matches("\\d{1,2}(:00|:30)?")
            || !getEndTimeInput().getText().matches("\\d{1,2}(:00|:30)?")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all time input fields and as either whole or half hour.");
            alert.showAndWait();
        } else {
            if (HttpRequestHandler.user != null) {
                String startTimeString = getStartTimeInput().getText();
                String endTimeString = getEndTimeInput().getText();
                Timestamp startTime = constructTimestamp(startTimeString);
                Timestamp endTime = constructTimestamp(endTimeString);

                ClientRequest<Reservation> reservationRequest = new ClientRequest<>(
                    HttpRequestHandler.user.getUsername(),
                    HttpRequestHandler.user.getUserKind(),
                    new Reservation(HttpRequestHandler.user, room, new TimeSlot(startTime, endTime))
                );

                ServerResponseAlert response =
                    httpRequestHandler.post("reservations/user/add", reservationRequest,
                            ServerResponseAlert.class);
                try {
                    Alert alert = new Alert(Alert.AlertType.valueOf(response.getAlertType()));
                    alert.setTitle("Response");
                    alert.setHeaderText(null);
                    alert.setContentText(response.getMessage());
                    alert.showAndWait();
                } catch (NullPointerException npe) {
                    //Do nothing
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("You must login before you can make a reservation");
                // TODO: Add a button that leads to login here
                alert.showAndWait();
            }
        }
    }

    /**
     * Gets the timeString and finds the chosen {@link LocalDate} from the {@link ChoiceBox} in ReservationsScene,
     * and constructs a {@link Timestamp} from it.
     * @param timeString A string in the format (H)H:MM (e.g. 12:00 or 9 or 13:30).
     * @return the timestamp for the wanted date and time.
     */
    public Timestamp constructTimestamp(String timeString) {
        ChoiceBox<LocalDate> dates = (ChoiceBox<LocalDate>) MainApp.getPrimaryStage().getScene().lookup("#datesList");
        LocalDate chosenDate = dates.getValue();
        if (timeString.matches("\\d{1,2}(:00)?")) {
            return new Timestamp(chosenDate.getYear() - 1900, chosenDate.getMonthValue() - 1, chosenDate.getDayOfMonth(),
                Integer.parseInt(timeString.split(":")[0]), 0, 0, 0);
        } else {
            return new Timestamp(chosenDate.getYear() - 1900, chosenDate.getMonthValue() - 1, chosenDate.getDayOfMonth(),
                Integer.parseInt(timeString.split(":")[0]), Integer.parseInt(timeString.split(":")[1]), 0, 0);
        }
    }

    public VBox getRoomBackground() {
        return controller.getBackground();
    }

    public Label getRoomName() {
        return controller.getRoomName();
    }

    public Label getHasMultimedia() {
        return controller.hasMultimedia();
    }

    public Label getCapacity() {
        return controller.getCapacity();
    }

    public TextField getStartTimeInput() {
        return controller.getStartTimeInput();
    }

    public TextField getEndTimeInput() {
        return controller.getEndTimeInput();
    }

}

