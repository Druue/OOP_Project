package nl.tudelft.oopp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import nl.tudelft.oopp.api.models.Details;
import nl.tudelft.oopp.api.models.RegistrationDetails;
import nl.tudelft.oopp.api.models.User;
import nl.tudelft.oopp.api.models.UserKind;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UserTest {

    User testUser;
    User identicalTestUser;
    User otherUser;

    @BeforeEach
    void beforeEach() {

        testUser = new User(
                new Details(
                        "first last",
                        "user desc",
                        "profile picture"
                ),
                "mail@example.com",
                "username",
                "pass123",
                UserKind.Student
        );

        identicalTestUser = new User(
                new Details(
                        "first last",
                        "user desc",
                        "profile picture"
                ),
                "mail@example.com",
                "username",
                "pass123",
                UserKind.Student
        );

        otherUser = new User(
                new Details(
                        "other name",
                        "user desc",
                        "profile picture"
                ),
                "othermail@example.com",
                "someone_else",
                "pass123",
                UserKind.Employee
        );

    }

    @Test
    void testConstructors() {
        User emptyUser = new User();

        assertNull(emptyUser.getUsername());
        assertNull(emptyUser.getEmail());
        assertNull(emptyUser.getId());
        assertNull(emptyUser.getDetails());
        assertNull(emptyUser.getUserKind());

        RegistrationDetails testRegistration = new RegistrationDetails(
                new Details(
                        "name user",
                        "desc",
                        "profle picture"
                ),
                "username",
                "email",
                "pass"
        );

        User registeredUser = new User(testRegistration);
        assertEquals("email", registeredUser.getEmail());
        assertEquals("desc", registeredUser.getDetails().getDescription());

    }

    @Test
    void testGetterSetters() {
        Details newDetails = new Details(
                "updated user",
                "new information",
                "new profile"
        );

        testUser.setDetails(newDetails);
        assertEquals(newDetails, testUser.getDetails());


        testUser.setEmail("newmail@mail.com");
        assertEquals("newmail@mail.com", testUser.getEmail());

        testUser.setId(42L);
        assertEquals(42L, testUser.getId());

        testUser.setUsername("newusername");
        assertEquals("newusername", testUser.getUsername());

        testUser.setUserKind(UserKind.Admin);
        assertEquals(UserKind.Admin, testUser.getUserKind());

        testUser.setPassword("badpass123");
        assertEquals("badpass123", testUser.getPassword());

        testUser.setName("new name");
        assertEquals("new name", testUser.getName());
    }

    @Test
    void testEquality() {
        assertEquals(testUser, identicalTestUser);
        assertEquals(testUser, testUser);

        assertNotEquals(testUser, otherUser);
        assertNotEquals(testUser, null);
    }

}
