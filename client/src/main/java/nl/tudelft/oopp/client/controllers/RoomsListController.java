package nl.tudelft.oopp.client.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import nl.tudelft.oopp.api.models.TestHourAndMinutes;
import nl.tudelft.oopp.api.models.TestOpeningTimes;
import nl.tudelft.oopp.api.models.TestTimeSlot;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RoomsListController implements Initializable {

    @FXML
    ListView<RoomEntryComponent> roomEntriesContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * This will get all the rooms of the selected building and create GUI for them, as well as the
     * initial tab layout.
     *
     * @param event The event that called the function. Used to find out what Building the request
     *              was generated from.
     */
    public void generateInitialRooms(MouseEvent event) {
        //        List<Room> roomsList = generateTestRooms();
        TestOpeningTimes myOpeningTimes = generateTestOpeningTimes();
        ObservableList<RoomEntryComponent> roomEntries = FXCollections.observableArrayList();

        //        for(Room myRoom:roomsList) {
        List<TestTimeSlot> timeSlots = generateTestTimeSlots();
        RoomEntryComponent roomEntry = new RoomEntryComponent();
        roomEntries.add(roomEntry);
        //        }

        roomEntriesContainer.setItems(roomEntries);


    }

    //    private static List<Room> generateTestRooms() {
    //        List<Room> result = new ArrayList<Room>();
    //        Details details = new Details(1, "test", "test", "test");
    //        Room room1 = new Room(Long.parseLong("1"),"Room1",details);
    //        result.add(room1);
    //        return result;
    //    }

    private static List<TestTimeSlot> generateTestTimeSlots() {
        List<TestTimeSlot> result = new ArrayList<TestTimeSlot>();
        for (int i = 0; i != 26; i++) {
            TestTimeSlot myTimeSlot = new TestTimeSlot(i, true);
            result.add(myTimeSlot);
        }
        return result;
    }

    private static TestOpeningTimes generateTestOpeningTimes() {
        TestHourAndMinutes openingTime = new TestHourAndMinutes(8, 30);
        TestHourAndMinutes closingTime = new TestHourAndMinutes(23, 0);

        return new TestOpeningTimes(openingTime, closingTime);
    }
}
