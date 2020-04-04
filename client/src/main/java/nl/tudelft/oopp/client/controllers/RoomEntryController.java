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

    //    /**
    //     * Generates a new timeline.
    //     *
    //     * @param openingTimes the opening times.
    //     * @param timeSlots    The list of timeslots to use.
    //     */
    //    public void generateTimeline(String startTime, String endTime) {
    //        timeline.maxWidthProperty().bind(roomEntryBackground.widthProperty().multiply(0.8));
    //        timeline.prefWidthProperty().bind(roomEntryBackground.widthProperty().multiply(0.8));
    //        timeline.minWidthProperty().bind(roomEntryBackground.widthProperty().multiply(0.8));
    //        timeline.setTranslateY(timeline.getPrefWidth() * (-1) / 2);
    //        timeline.setAlignment(Pos.TOP_LEFT);
    //
    //        boolean startsWithRoundHour = isCloserToRoundHour(startTime);
    //        if (!startsWithRoundHour) {
    //            Label hour = new Label((startTime.split(":")[0] + ":30"));
    //            hour.setFont(Font.font(10));
    //            hour.setTranslateX(-10);
    //            hour.translateYProperty().bind(timeline.heightProperty().divide(1.9));
    //            timeline.getChildren().add(hour);
    //        }
    //
    //        int numTimeSlots = calculateNumTimeSlots(startTime, endTime);
    //        for (int i = 0; i != numTimeSlots; i++) {
    //            Rectangle timeSlot = new Rectangle();
    //            timeSlot.setId("timeslot" + i);
    //            timeSlot.widthProperty().bind(timeline.widthProperty().divide(numTimeSlots));
    //            timeSlot.heightProperty().bind(timeline.heightProperty().divide(2));
    //            timeSlot.translateXProperty().bind(timeSlot.widthProperty().multiply(i));
    //            timeSlot.setFill(Color.valueOf("808080"));
    //
    //            if ((startsWithRoundHour && i % 2 == 0) || (!startsWithRoundHour && i % 2 != 0)) {
    //                Rectangle fullHourMark = new Rectangle();
    //                Label hour = new Label((openingTimes.getOpeningHour().getHour() + i / 2 + i % 2) + "");
    //                hour.setFont(Font.font(11));
    //
    //                fullHourMark.heightProperty().bind(timeSlot.heightProperty().multiply(1.2));
    //                fullHour
    //                fullHourMark.translateXProperty().bind(timeSlot.widthProperty().multiply(i)
    //                        .subtract(timeline.getPrefWidth() / numTimeSlots / 2));
    //                fullHourMark.setFill(Color.valueOf("000000")); //TODO: Set it as a string constant
    //
    //                int hourTranslateX;
    //                if (Integer.parseInt(hour.getText()) < 10) {
    //                    hourTranslateX = 2;
    //                } else {
    //                    hourTranslateX = 5;
    //                }
    //                hour.translateXProperty().bind(timeSlot.widthProperty().multiply(i)
    //                        .subtract(timeline.getPrefWidth() / numTimeSlots / 2 + hourTranslateX));
    //                hour.translateYProperty().bind(timeSlot.heightProperty().multiply(1.2));
    //
    //                timeline.getChildren().addAll(timeSlot, fullHourMark, hour);
    //            } else {
    //                Rectangle halfHourMark = new Rectangle();
    //                halfHourMark.heightProperty().bind(timeSlot.heightProperty().multiply(1.1));
    //                halfHourMark.setWidth(1);
    //                halfHourMark.translateXProperty().bind(timeSlot.widthProperty().multiply(i)
    //                        .subtract(timeline.getPrefWidth() / numTimeSlots / 2));
    //                halfHourMark.setFill(Color.valueOf("000000"));
    //
    //                Label hour = new Label((openingTimes.getOpeningHour().getHour() + i / 2) + "");
    //                hour.translateXProperty().bind(timeSlot.widthProperty().multiply(i)
    //                        .subtract(timeline.getPrefWidth() / numTimeSlots / 2));
    //                hour.translateYProperty().bind(timeSlot.heightProperty().multiply(1.1));
    //
    //                timeline.getChildren().addAll(timeSlot, halfHourMark);
    //            }
    //
    //            EventHandler<MouseEvent> mouseClickHandler = new EventHandler<MouseEvent>() {
    //                @Override
    //                public void handle(MouseEvent mouseEvent) {
    //                    timeSlot.setFill(Color.GREEN);
    //                }
    //            };
    //
    //            timeSlot.setOnMouseClicked(mouseClickHandler); //TODO: Implement mouse drag selection
    //
    //
    //            this.timeSlots.add(timeSlot);
    //        }
    //
    //        if (openingTimes.getClosingHour().getMinutes() > 30 || openingTimes.getClosingHour().getMinutes() == 0) {
    //            Label hour = new Label(openingTimes.getClosingHour().getHour() + "");
    //            hour.setFont(Font.font(11));
    //            hour.translateXProperty().bind(timeline.widthProperty());
    //            hour.translateYProperty().bind(timeline.heightProperty().divide(1.7));
    //
    //            Rectangle fullHourMark = new Rectangle();
    //            fullHourMark.heightProperty().bind(timeline.heightProperty().multiply(0.6));
    //            fullHourMark.setWidth(2);
    //            fullHourMark.translateXProperty().bind(timeline.widthProperty());
    //            fullHourMark.setFill(Color.valueOf("000000"));
    //
    //            int hourTranslateX;
    //            if (Integer.parseInt(hour.getText()) < 10) {
    //                hourTranslateX = 2;
    //            } else {
    //                hourTranslateX = 5;
    //            }
    //            hour.translateXProperty().bind(timeline.widthProperty().subtract(hourTranslateX));
    //            hour.translateYProperty().bind(timeline.heightProperty().multiply(0.6));
    //
    //            timeline.getChildren().addAll(fullHourMark, hour);
    //        } else {
    //            Label hour = new Label((openingTimes.getClosingHour().getHour() + ":30"));
    //            hour.setFont(Font.font(10));
    //            hour.setTranslateX(-10);
    //
    //            hour.translateYProperty().bind(timeline.heightProperty().divide(1.9));
    //            timeline.getChildren().add(hour);
    //        }
    //
    //        //        timeline.getChildren().add(area);
    //    }
    //
    //    /**
    //     * Says if it's closer to half hour or to round.
    //     * @param time A {@link String} of format (H)H:MM.
    //     * @return true if minutes are 0 or between 31 and 59. Else false.
    //     */
    //    private boolean isCloserToRoundHour(String time) {
    //        int minutes = Integer.parseInt(time.split(":")[1], 10);
    //        return minutes == 0 || (minutes < 60 && minutes > 30);
    //    }
    //
    //    private int calculateNumTimeSlots(String startTime, String endTime) {
    //        int startHour = Integer.parseInt(startTime.split(":")[0]);
    //        int startMinutes = Integer.parseInt(startTime.split(":")[1], 10);
    //        int endHour = Integer.parseInt(endTime.split(":")[0]);
    //        int endMinutes = Integer.parseInt(endTime.split(":")[1], 10);
    //
    //        int result = (endHour - startHour) * 2;
    //        if(startMinutes > 0 && startMinutes < 30 && endMinutes < 30) {
    //            return result - 1;
    //        }
    //
    //    }

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

