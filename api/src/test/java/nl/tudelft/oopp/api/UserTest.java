package nl.tudelft.oopp.api;

import nl.tudelft.oopp.api.models.Details;
import nl.tudelft.oopp.api.models.User;
import nl.tudelft.oopp.api.models.UserKind;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    User testUser;
    User testUser_identical;
    User testAdmin;
    @BeforeEach
    void beforeAll() {
        testUser = new User(
                new Details(
                        "name",
                        "description",
                        "profile picture"
                ),
                "mail@example.com",
                "test_user",
                "password123",
                UserKind.Student
        );
        testUser_identical = new User(
                new Details(
                        "name",
                        "description",
                        "profile picture"
                ),
                "mail@example.com",
                "test_user",
                "password123",
                UserKind.Student
        );

        testAdmin = new User(
                new Details(
                        "admin",
                        "description",
                        "profile picture"
                ),
                "admin@example.com",
                "test_admin",
                "admin",
                UserKind.Admin
        );
    }


    @Test
    public void saveUserTest() {
        beforeAll();
        HttpRequestHandler.saveUser(testUser);
        assertEquals(testUser.getEmail(), HttpRequestHandler.user.getEmail());
    }

}