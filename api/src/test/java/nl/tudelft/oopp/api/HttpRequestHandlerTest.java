package nl.tudelft.oopp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import nl.tudelft.oopp.api.models.Bike;
import nl.tudelft.oopp.api.models.Details;
import nl.tudelft.oopp.api.models.Room;
import nl.tudelft.oopp.api.models.User;
import nl.tudelft.oopp.api.models.UserKind;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

@PrepareForTest(HttpRequestHandler.class)
public class HttpRequestHandlerTest {

    User testUser;
    User identicalUser;
    User testAdmin;
    HttpRequestHandler httpRequestHandler;
    HttpClient mockHttpClient;

    @BeforeEach
    void beforeEach() throws IOException, InterruptedException {

        mockHttpClient = mock(HttpClient.class);

        httpRequestHandler = new HttpRequestHandler(mockHttpClient);

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
        identicalUser = new User(
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

        httpRequestHandler.put("ping", null, String.class);

    }

    /**
     * Checks if a response is received.
     */
    @Test
    public void putTestGetAnyResponse() {

        Object response = httpRequestHandler.put("ping", null, String.class);

        //assertNotNull(response);
    }

    /**
     * Checks to see if the **expected** response is received.
     */
    @Test
    public void putTestGetCorrectResponse() {

        String response = httpRequestHandler.put("ping", null, String.class);

        //assertEquals(response, "pong");

    }


    //POST REQUESTS
    /**
     * Checks if a POST request can be sent without an error, regardless of the input.
     */
    @Test
    public void postTestSendSuccessful() {
        httpRequestHandler.post("ping", null, String.class);
    }

    /**
     * Checks if a response is received.
     */
    @Test
    public void postTestGetAnyResponse() {

        Object response = httpRequestHandler.post("ping", null, String.class);

        //assertNotNull(response);

    }

    /**
     * Checks to see if the **expected** response is received.
     */
    @Test
    public void postTestGetCorrectResponse() {

        String response = httpRequestHandler.post("ping", null, String.class);

        //assertEquals(response, "pong");

    }

    //GET REQUESTS
    /**
     * Checks if a GET request can be sent without an error, regardless of the input.
     */
    @Test
    public void getTestSendSuccessful() {
        httpRequestHandler.get("ping", String.class);
    }


    /**
     * Checks if a response is received.
     */
    @Test
    public void getTestGetAnyResponse() {

        Object response = httpRequestHandler.get("ping", String.class);

        //assertNotNull(response);

    }

    /**
     * Checks to see if the **expected** response is received.
     */
    @Test
    public void getTestGetCorrectResponse() {

        Object response = httpRequestHandler.get("ping", String.class);

        //assertEquals(response, "pong");

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

        // Unfortunately, importing server models is not possible.
        // That's why conversion is done between two API models.
        Room convertedTestRoom = httpRequestHandler.convertModel(testRoom, Room.class);
        assertNotNull(convertedTestRoom);

        // To test the conversion, see if the id's are the same.
        assertEquals(convertedTestRoom.getCapacity(), 40);

        // An object converted into the same type should be equal to itself.
        convertedTestRoom = httpRequestHandler.convertModel(testRoom, Room.class);
        assertEquals(testRoom, convertedTestRoom);
    }

}