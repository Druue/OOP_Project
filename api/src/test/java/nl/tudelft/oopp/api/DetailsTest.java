package nl.tudelft.oopp.api;

import nl.tudelft.oopp.api.models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static nl.tudelft.oopp.api.HttpRequestHandler.put;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DetailsTest {

    Details testDetails;
    Details identicalTestDetails;
    Details otherDetails;

    @BeforeEach
    void beforeEach() {
        testDetails = new Details(
                4L,
                "test",
                "details",
                "object"
        );

        identicalTestDetails = new Details(
                4L,
                "test",
                "details",
                "object"
        );

        otherDetails = new Details(
                50L,
                "other",
                "details",
                "object"
        );
    }

    @Test
    void testEquality() {
        assertEquals(testDetails, identicalTestDetails);
        assertEquals(testDetails, testDetails);

        assertNotEquals(testDetails, otherDetails);
        assertNotEquals(testDetails, null);
    }

    @Test
    void testGetterSetters() {
        testDetails.setId(5L);
        assertEquals(5L, testDetails.getId());

        testDetails.setName("foobar");
        assertEquals("foobar", testDetails.getName());

        testDetails.setDescription("new description");
        assertEquals("new description", testDetails.getDescription());

        testDetails.setImage("imageURL");
        assertEquals("imageURL", testDetails.getImage());

    }

    @Test
    void constructorTest() {
        Details newDetails = new Details();
        assertNull(newDetails.getId());
        Details anotherDetails = new Details(
                "another details",
                "desc",
                "image"
        );

        assertNull(anotherDetails.getId());

        assertEquals("another details", anotherDetails.getName());
        assertEquals("desc", anotherDetails.getDescription());
        assertEquals("image", anotherDetails.getImage());
    }

}