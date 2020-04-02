package nl.tudelft.oopp.client.controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
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

            //            controller.generateTimeline(generateTestOpeningTimes(), generateTestTimeSlots());
            controller.getReserveButton().setOnAction(//TODO: replace with lambda expression
                new EventHandler<>() {
                    @Override
                    public void handle(ActionEvent event) {
                        reserveRoom();
                    }
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
                Timestamp startTime;
                Timestamp endTime;
                if (startTimeString.matches("\\d{1,2}(:00)?")) {
                    startTime = new Timestamp(0, 0, 0, Integer.parseInt(startTimeString.split(":")[0]),
                        0, 0, 0);
                } else {
                    startTime = new Timestamp(0, 0, 0, Integer.parseInt(startTimeString.split(":")[0]),
                        Integer.parseInt(startTimeString.split(":")[1]), 0, 0);
                }

                if (endTimeString.matches("\\d{1,2}(:00)?")) {
                    endTime = new Timestamp(0, 0, 0, Integer.parseInt(endTimeString.split(":")[0]),
                        0, 0, 0);
                } else {
                    endTime = new Timestamp(0, 0, 0, Integer.parseInt(endTimeString.split(":")[0]),
                        Integer.parseInt(endTimeString.split(":")[1]), 0, 0);
                }

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

    public Rectangle getTimeslot(int index) {
        return controller.getTimeslot(index);
    }

    public List<Rectangle> getTimeslots() {
        return controller.getTimeslots();
    }

    public TextField getStartTimeInput() {
        return controller.getStartTimeInput();
    }

    public TextField getEndTimeInput() {
        return controller.getEndTimeInput();
    }

}

