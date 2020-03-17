package nl.tudelft.oopp.client.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import nl.tudelft.oopp.api.models.TestHourAndMinutes;
import nl.tudelft.oopp.api.models.TestOpeningTimes;
import nl.tudelft.oopp.api.models.TestTimeSlot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomEntryComponent extends Pane {

    RoomEntryController controller;

    public RoomEntryComponent() {
        super();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/roomEntry.fxml"));

            controller = new RoomEntryController();

            loader.setController(controller);

            Pane roomEntry = loader.load();
            controller.generateTimeline(generateTestOpeningTimes(), generateTestTimeSlots());

            this.getChildren().add(roomEntry);

        } catch (IOException e) {
            System.out.println("Error loading room entry");
        }


    }

    private static List<TestTimeSlot> generateTestTimeSlots() {
        List<TestTimeSlot> result = new ArrayList<TestTimeSlot>();
        for(int i = 0; i != 29; i++) {
            TestTimeSlot myTimeSlot = new TestTimeSlot(i,true);
            result.add(myTimeSlot);
        }
        return result;
    }

    private static TestOpeningTimes generateTestOpeningTimes() {
        TestHourAndMinutes openingTime = new TestHourAndMinutes(8, 30);
        TestHourAndMinutes closingTime = new TestHourAndMinutes(23,0);

        return new TestOpeningTimes(openingTime, closingTime);
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

}

