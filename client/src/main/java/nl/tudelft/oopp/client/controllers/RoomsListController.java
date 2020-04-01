package nl.tudelft.oopp.client.controllers;

import static nl.tudelft.oopp.client.controllers.ReservationsSceneController.RESPONSE_TIMEOUT;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.Room;
import nl.tudelft.oopp.api.models.RoomResponse;
import nl.tudelft.oopp.api.models.TestHourAndMinutes;
import nl.tudelft.oopp.api.models.TestOpeningTimes;
import nl.tudelft.oopp.api.models.TestTimeSlot;

public class RoomsListController implements Initializable {

    private static final HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

    @FXML
    ListView<RoomEntryComponent> roomEntriesContainer;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //    private static List<Room> generateTestRooms() {
    //        List<Room> result = new ArrayList<Room>();
    //        Details details = new Details(1, "test", "test", "test");
    //        Room room1 = new Room(Long.parseLong("1"),"Room1",details);
    //        result.add(room1);
    //        return result;
    //    }

    /**
     * This will get all the rooms of the selected building and create GUI for them, as well as the
     * initial tab layout.
     *
     * @param event The event that called the function. Used to find out what Building the request
     *              was generated from.
     */
    public void generateInitialRooms(MouseEvent event) {
        RoomResponse roomResponse = httpRequestHandler.get("reservables/all/rooms",
                RoomResponse.class);
        if (waitForResponse(roomResponse)) {
            List<Room> roomsList = roomResponse.getRoomList();
            TestOpeningTimes myOpeningTimes = generateTestOpeningTimes();
            ObservableList<RoomEntryComponent> roomEntries = FXCollections.observableArrayList();

            for (Room myRoom : roomsList) {
                //List<TestTimeSlot> timeSlots = generateTestTimeSlots();
                RoomEntryComponent roomEntry = new RoomEntryComponent(myRoom);
                roomEntries.add(roomEntry);
            }
            roomEntriesContainer.setItems(roomEntries);
        }

    }

    /**
     * Polls each second whether the buildingList was received by the BuildingResponse
     * until success or timeout.
     *
     * @param roomResponse The response object.
     * @return boolean whether a (non-null)response was received
     */
    private boolean waitForResponse(RoomResponse roomResponse) {
        int i = 0;
        while (i != RESPONSE_TIMEOUT) {
            if (roomResponse != null && roomResponse.getRoomList() != null) {
                return true;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                System.out.println("Problems with BuildingResponse in ReservationsSceneController.waitForResponse()");
                return false;
            }
            i++;
        }
        System.out.println("BuildingResponse timed out in ReservationsSceneController");
        return false;
    }
}
