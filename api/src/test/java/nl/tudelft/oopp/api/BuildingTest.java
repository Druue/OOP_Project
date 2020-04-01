package nl.tudelft.oopp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.api.models.Building;
import nl.tudelft.oopp.api.models.BuildingResponse;
import nl.tudelft.oopp.api.models.Details;
import nl.tudelft.oopp.api.models.TimeSlot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * These tests cover the Building, BuildingResponse and BuildingsDetails class.
 */
public class BuildingTest {

    Building testBuilding;
    Building identicalBuilding;
    Building anotherBuilding;

    BuildingResponse response;


    @BeforeEach
    void beforeEach() {
        testBuilding = new Building(
                42L,
                new Details(
                        "building",
                        "desc",
                        "img"
                ),
                new TimeSlot(
                        new Timestamp(4L),
                        new Timestamp(5L)
                )
        );

        identicalBuilding = new Building(
                42L,
                new Details(
                        "building",
                        "desc",
                        "img"
                ),
                new TimeSlot(
                        new Timestamp(4L),
                        new Timestamp(5L)
                )
        );

        anotherBuilding = new Building(
                600L,
                new Details(
                        "another building",
                        "desc",
                        "img"
                ),
                new TimeSlot(
                        new Timestamp(40L),
                        new Timestamp(50L)
                )
        );
        List<Building> list = new ArrayList<>();
        list.add(testBuilding);
        list.add(anotherBuilding);
        response = new BuildingResponse(list);


    }


    @Test
    void testEquality() {
        assertEquals(testBuilding, identicalBuilding);
        assertEquals(testBuilding, testBuilding);

        assertNotEquals(testBuilding, anotherBuilding);
        assertNotEquals(testBuilding, null);
    }

    @Test
    void testGetterSetters() {
        //Building
        testBuilding.setNumber(9L);
        assertEquals(9L, testBuilding.getNumber());

        testBuilding.setName("name");
        assertEquals("name", testBuilding.getName());

        Details details = new Details("d", "e", "tails");
        testBuilding.setDetails(details);
        assertEquals(testBuilding.getDetails(), details);

        response.setBuildingList(new ArrayList<>());
        assertTrue(response.getBuildingList().isEmpty());

        testBuilding.setOpeningHours(
                new TimeSlot(
                        new Timestamp(42L),
                        new Timestamp(52L)
                )
        );
        assertEquals(testBuilding.getOpeningHours().getStartTime().getTime(), 42L);

    }

    @Test
    void testConstructors() {
        Building emptyBuilding = new Building();

        assertNull(emptyBuilding.getNumber());

        Building incompleteBuilding = new Building(5L, null);
        assertNull(incompleteBuilding.getOpeningHours());
    }



}
