package nl.tudelft.oopp.api;

import nl.tudelft.oopp.api.models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static nl.tudelft.oopp.api.HttpRequestHandler.put;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HttpRequestHandlerTest {

    User testUser;
    User testUser_identical;
    User testAdmin;
    HttpRequestHandler mockHandler;

    @BeforeEach
    void beforeEach() {
        // Used for get, post, put request mocking
        mockHandler = mock(HttpRequestHandler.class);

//        when(mockHandler.put("ping", null, String.class)).thenReturn("pong");
//        when(mockHandler.post("ping", null, String.class)).thenReturn("pong");
//        when(mockHandler.get("ping",  String.class)).thenReturn("pong");

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

    /**
     * Checks if a PUT request can be sent without an error, regardless of the input.
     */
    @Test
    public void putTestSendSuccessful() {

        HttpRequestHandler.put("ping", null, String.class);

    }

    /**
     * Checks if a response is received.
     */
    @Test
    public void putTestGetAnyResponse() {

        Object response = mockHandler.put("ping", null, String.class);

        assertNotNull(response);
    }

    /**
     * Checks to see if the **expected** response is received.
     */
    @Test
    public void putTestGetCorrectResponse() {

        String response = mockHandler.put("ping", null, String.class);

        assertEquals(response, "pong");

    }


    //POST REQUESTS
    /**
     * Checks if a POST request can be sent without an error, regardless of the input.
     */
    @Test
    public void postTestSendSuccessful() {
        HttpRequestHandler.post("ping", null, String.class);
    }

    /**
     * Checks if a response is received.
     */
    @Test
    public void postTestGetAnyResponse() {

        Object response = mockHandler.post("ping", null, String.class);

        assertNotNull(response);

    }

    /**
     * Checks to see if the **expected** response is received.
     */
    @Test
    public void postTestGetCorrectResponse() {

        String response = mockHandler.post("ping", null, String.class);

        assertEquals(response, "pong");

    }

    //GET REQUESTS
    /**
     * Checks if a GET request can be sent without an error, regardless of the input.
     */
    @Test
    public void getTestSendSuccessful() {
        HttpRequestHandler.get("ping", String.class);
    }


    /**
     * Checks if a response is received.
     */
    @Test
    public void getTestGetAnyResponse() {

        Object response = mockHandler.get("ping", String.class);

        assertNotNull(response);

    }

    /**
     * Checks to see if the **expected** response is received.
     */
    @Test
    public void getTestGetCorrectResponse() {

        Object response = mockHandler.get("ping", String.class);

        assertEquals(response, "pong");

    }

    /**
     * Tests convertModel() by converting a room into a bike. Both share a details attribute,
     * and this attribute should be preserved when converting.
     */
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

        // To test the conversion, see if the id's are the same.
        assertEquals(testBike.getDetails().id, testRoom.getDetails().id);

        // An object converted into the same type should be equal to itself.
        Room convertedTestRoom = HttpRequestHandler.convertModel(testRoom, Room.class);
        assertEquals(testRoom, convertedTestRoom);
    }

}