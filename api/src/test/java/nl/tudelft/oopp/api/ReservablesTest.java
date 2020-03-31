package nl.tudelft.oopp.api;

import nl.tudelft.oopp.api.models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static nl.tudelft.oopp.api.HttpRequestHandler.put;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReservablesTest {

    Bike testBike;
    Bike identicalTestBike;
    Bike otherBike;

    Room testRoom;
    Room identicalTestRoom;
    Room otherRoom;

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
    }

    @Test
    void equalityTest() {

        assertEquals(testRoom, testRoom);
        assertEquals(testRoom, identicalTestRoom);
        assertNotEquals(testRoom, otherRoom);


        assertNotEquals(testRoom, testBike);

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


}
