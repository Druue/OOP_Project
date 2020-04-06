package nl.tudelft.oopp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import nl.tudelft.oopp.api.models.Details;
import nl.tudelft.oopp.api.models.RegistrationDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DetailsTest {

    Details testDetails;
    Details identicalTestDetails;
    Details otherDetails;

    RegistrationDetails testRegistrationDetails;

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

        testRegistrationDetails = new RegistrationDetails(
                testDetails,
                "username",
                "email@email.com",
                "1234"
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


        testRegistrationDetails.setDetails(otherDetails);
        assertEquals(otherDetails, testRegistrationDetails.getDetails());

        testRegistrationDetails.setEmail("newmail@gmail.org");
        assertEquals("newmail@gmail.org", testRegistrationDetails.getEmail());

        testRegistrationDetails.setPassword("4321");
        assertEquals("4321", testRegistrationDetails.getPassword());

        testRegistrationDetails.setusername("usr");
        assertEquals("usr", testRegistrationDetails.getusername());
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