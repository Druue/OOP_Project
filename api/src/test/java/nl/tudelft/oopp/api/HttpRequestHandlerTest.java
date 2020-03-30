package nl.tudelft.oopp.api;

import nl.tudelft.oopp.api.models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HttpRequestHandlerTest {

    User testUser;
    User testUser_identical;
    User testAdmin;
    @BeforeEach
    void beforeEach() {
        testUser = new User(
                new Details(
                        "name",
                        "description",
                        "profile picture"
                ),
                "mail@student.tudelft.nl",
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
                "admin@admin.tudelft.nl",
                "test_admin",
                "admin",
                UserKind.Admin
        );
    }


    @Test
    public void saveUserTest() {
        HttpRequestHandler.saveUser(testUser);
        assertEquals(testUser.getEmail(), HttpRequestHandler.user.getEmail());
    }

    //PUT REQUESTS

    @Test
    public void putTestSendSuccesful() {

    }

    @Test
    public void putTestGetResponse() {

    }

    @Test
    public void putTestGetCorrectResponseType() {

    }


    //POST REQUESTS

    @Test
    public void postTestSendSuccesful() {

    }

    @Test
    public void postTestGetResponse() {

    }

    @Test
    public void postTestGetCorrectResponseType() {

    }

    //GET REQUESTS
    @Test
    public void getTestGetResponse() {

    }

    @Test
    public void getTestGerCorrectResponseType() {

    }

    // convertModel
    @Test
    public void convertModelTest() {
        Room testRoom = new Room(
                8L,
                new Details(
                        "Broom",
                        "description",
                        "image url"
                ),
                40,
                false,
                false
        );

        // For my next trick, I shall convert a Room into a Bike.
        Bike testBike = HttpRequestHandler.convertModel(testRoom, Bike.class);

        System.out.println(testBike.details.name);
    }

}