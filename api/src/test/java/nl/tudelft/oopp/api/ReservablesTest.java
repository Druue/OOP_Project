package nl.tudelft.oopp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.api.models.Bike;
import nl.tudelft.oopp.api.models.Details;
import nl.tudelft.oopp.api.models.Reservable;
import nl.tudelft.oopp.api.models.ReservableResponse;
import nl.tudelft.oopp.api.models.Room;
import nl.tudelft.oopp.api.models.RoomResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReservablesTest {

    Bike testBike;
    Bike identicalTestBike;
    Bike otherBike;

    Room testRoom;
    Room identicalTestRoom;
    Room otherRoom;

    ReservableResponse reservableResponse;

    RoomResponse roomResponse;

    @BeforeEach
    void beforeEach() {
        testBike = new Bike(
                42L,
                new Details(
                        "test_bike",
                        "description",
                        "image"
                )
        );

        identicalTestBike = new Bike(
                42L,
                new Details(
                        "test_bike",
                        "description",
                        "image"
                )
        );

        otherBike = new Bike(
                66L,
                new Details(
                        "other_bike",
                        "other description",
                        "other image"
                )
        );


        testRoom = new Room(
                42L,
                new Details(
                        "test_bike",
                        "description",
                        "image"
                ),
                40,
                true,
                false
        );

        identicalTestRoom = new Room(
                42L,
                new Details(
                        "test_bike",
                        "description",
                        "image"
                ),
                40,
                true,
                false
        );

        otherRoom = new Room(
                50L,
                new Details(
                        "other_room",
                        "description",
                        "image"
                ),
                40,
                true,
                true
        );

        List<Reservable> list = new ArrayList<>();
        list.add(testBike);
        list.add(testRoom);
        reservableResponse = new ReservableResponse(list);

        List<Room> roomList = new ArrayList<>();
        roomList.add(testRoom);
        roomList.add(otherRoom);
        roomResponse = new RoomResponse(roomList);
    }

    @Test
    void equalityTest() {

        assertEquals(testRoom, testRoom);
        assertEquals(testRoom, identicalTestRoom);
        assertNotEquals(testRoom, otherRoom);


        assertNotEquals(testRoom, testBike);
        assertNotEquals(testBike, null);

        assertEquals(testBike, testBike);
        assertEquals(testBike, identicalTestBike);
        assertNotEquals(testBike, otherBike);

    }

    @Test
    void testGetterSetters() {
        // Used for getting/setting
        Details testDetails = new Details("test", "details", "object");

        //Bikes
        testBike.setDetails(testDetails);
        assertEquals(testBike.getDetails(),testDetails);

        testBike.setId(4000L);
        assertEquals(4000L, testBike.getId());


        //Rooms
        testRoom.setDetails(testDetails);
        assertEquals(testDetails, testRoom.getDetails());

        testRoom.setCapacity(4000);
        assertEquals(4000, testRoom.getCapacity());

        testRoom.setForEmployee(true);
        assertTrue(testRoom.isForEmployee());

        testRoom.setHasProjector(true);
        assertTrue(testRoom.isHasProjector());
    }

    @Test
    void testResponses() {
        assertEquals(reservableResponse.getReservableList().size(), 2);

        reservableResponse.setReservableList(new ArrayList<>());
        assertTrue(reservableResponse.getReservableList().isEmpty());

        assertEquals(roomResponse.getRoomList().size(), 2);

        roomResponse.setRoomList(new ArrayList<>());
        assertTrue(roomResponse.getRoomList().isEmpty());
    }

    @Test
    void testConstructor() {
        Room emptyRoom = new Room();

        assertNull(emptyRoom.getDetails());
        assertNull(emptyRoom.getId());

    }

}
