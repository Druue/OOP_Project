package nl.tudelft.oopp.client.controllers;

import static nl.tudelft.oopp.client.controllers.ReservationsSceneController.RESPONSE_TIMEOUT;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.Building;
import nl.tudelft.oopp.api.models.Reservable;
import nl.tudelft.oopp.api.models.ReservableResponse;
import nl.tudelft.oopp.api.models.Room;
import nl.tudelft.oopp.api.models.RoomResponse;

public class RoomsListController implements Initializable {

    private static final HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

    @FXML
    private ListView<RoomEntryComponent> roomEntriesContainer;
    @FXML
    private Label startTimeLabel;
    @FXML
    private Label endTimeLabel;

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
     * This will get all the rooms of the selected {@link Building} and create GUI for them, as well as the
     * initial tab layout.
     *
     * @param building The {@link Building} from which to take the rooms (and opening hours)
     */
    public void generateInitialRooms(Building building) {
        startTimeLabel.setText(ReservationsSceneController.hourAndMinutesString(building.getOpeningHours().getStartTime()));
        endTimeLabel.setText(ReservationsSceneController.hourAndMinutesString(building.getOpeningHours().getEndTime()));
        ReservableResponse reservableResponse =
            httpRequestHandler.get("reservables/user/all/room/building/" + building.getNumber(), ReservableResponse.class);
        if (reservableResponse.getReservableList() != null) {
            ObservableList<RoomEntryComponent> roomEntries = FXCollections.observableArrayList();
            for (Reservable reservable:reservableResponse.getReservableList()) {
                if (reservable instanceof Room) {
                    Room myRoom = (Room) reservable;
                    RoomEntryComponent roomEntry = new RoomEntryComponent(myRoom);
                    roomEntries.add(roomEntry);
                }
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
