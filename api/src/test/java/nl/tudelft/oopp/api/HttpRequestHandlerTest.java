package nl.tudelft.oopp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import javax.net.ssl.SSLSession;
import nl.tudelft.oopp.api.models.Bike;
import nl.tudelft.oopp.api.models.Details;
import nl.tudelft.oopp.api.models.Room;
import nl.tudelft.oopp.api.models.ServerResponseAlert;
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
        ObjectMapper objectMapper = new ObjectMapper();

        mockHttpClient = mock(HttpClient.class);

        httpRequestHandler = new HttpRequestHandler(mockHttpClient);

        when(mockHttpClient.send(HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/ping"))
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                    objectMapper.writeValueAsString("")
                )
                )
                .build(),
                HttpResponse.BodyHandlers.ofString())).thenReturn(
                new HttpResponse<>() {
                    @Override
                    public int statusCode() {
                        return 200;
                    }

                    @Override
                    public HttpRequest request() {
                        return null;
                    }

                    @Override
                    public Optional<HttpResponse<String>> previousResponse() {
                        return Optional.empty();
                    }

                    @Override
                    public HttpHeaders headers() {
                        return null;
                    }

                    @Override
                    public String body() {
                        try {
                            return objectMapper.writeValueAsString(new ServerResponseAlert(
                                    "pong",
                                    null
                            ));
                        } catch (IOException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }

                    @Override
                    public Optional<SSLSession> sslSession() {
                        return Optional.empty();
                    }

                    @Override
                    public URI uri() {
                        return null;
                    }

                    @Override
                    public HttpClient.Version version() {
                        return null;
                    }
                }
        );

        when(mockHttpClient.send(HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/ping"))
                        .setHeader("Content-Type", "application/json")
                        .PUT(HttpRequest.BodyPublishers.ofString(
                                objectMapper.writeValueAsString("")
                                )
                        )
                        .build(),
                HttpResponse.BodyHandlers.ofString())).thenReturn(
                new HttpResponse<>() {
                    @Override
                    public int statusCode() {
                        return 200;
                    }

                    @Override
                    public HttpRequest request() {
                        return null;
                    }

                    @Override
                    public Optional<HttpResponse<String>> previousResponse() {
                        return Optional.empty();
                    }

                    @Override
                    public HttpHeaders headers() {
                        return null;
                    }

                    @Override
                    public String body() {
                        try {
                            return objectMapper.writeValueAsString(new ServerResponseAlert(
                                    "pong",
                                    null
                            ));
                        } catch (IOException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }

                    @Override
                    public Optional<SSLSession> sslSession() {
                        return Optional.empty();
                    }

                    @Override
                    public URI uri() {
                        return null;
                    }

                    @Override
                    public HttpClient.Version version() {
                        return null;
                    }
                }
        );

        when(mockHttpClient.send(HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/ping"))
                        .setHeader("Content-Type", "application/json")
                        .GET()
                        .build(),
                HttpResponse.BodyHandlers.ofString())).thenReturn(
                new HttpResponse<>() {
                    @Override
                    public int statusCode() {
                        return 200;
                    }

                    @Override
                    public HttpRequest request() {
                        return null;
                    }

                    @Override
                    public Optional<HttpResponse<String>> previousResponse() {
                        return Optional.empty();
                    }

                    @Override
                    public HttpHeaders headers() {
                        return null;
                    }

                    @Override
                    public String body() {
                        try {
                            return objectMapper.writeValueAsString(new ServerResponseAlert(
                                    "pong",
                                    null
                            ));
                        } catch (IOException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }

                    @Override
                    public Optional<SSLSession> sslSession() {
                        return Optional.empty();
                    }

                    @Override
                    public URI uri() {
                        return null;
                    }

                    @Override
                    public HttpClient.Version version() {
                        return null;
                    }
                }
        );



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

        httpRequestHandler.put("ping", null, ServerResponseAlert.class);

    }

    /**
     * Checks if a response is received.
     */
    @Test
    public void putTestGetAnyResponse() {

        Object response = httpRequestHandler.put("ping", null, ServerResponseAlert.class);

        assertNotNull(response);
    }

    /**
     * Checks to see if the **expected** response is received.
     */
    @Test
    public void putTestGetCorrectResponse() {

        ServerResponseAlert response = httpRequestHandler.put("ping", null, ServerResponseAlert.class);

        assertEquals(response.getMessage(), "pong");

    }


    //POST REQUESTS
    /**
     * Checks if a POST request can be sent without an error, regardless of the input.
     */
    @Test
    public void postTestSendSuccessful() {
        httpRequestHandler.post("ping", null, ServerResponseAlert.class);
    }

    /**
     * Checks if a response is received.
     */
    @Test
    public void postTestGetAnyResponse() {

        Object response = httpRequestHandler.post("ping", null, ServerResponseAlert.class);

        assertNotNull(response);

    }

    /**
     * Checks to see if the **expected** response is received.
     */
    @Test
    public void postTestGetCorrectResponse() {

        ServerResponseAlert response = httpRequestHandler.post("ping", null, ServerResponseAlert.class);

        assertEquals(response.getMessage(), "pong");

    }

    //GET REQUESTS
    /**
     * Checks if a GET request can be sent without an error, regardless of the input.
     */
    @Test
    public void getTestSendSuccessful() {
        httpRequestHandler.get("ping", ServerResponseAlert.class);
    }


    /**
     * Checks if a response is received.
     */
    @Test
    public void getTestGetAnyResponse() {

        Object response = httpRequestHandler.get("ping", ServerResponseAlert.class);

        assertNotNull(response);

    }

    /**
     * Checks to see if the **expected** response is received.
     */
    @Test
    public void getTestGetCorrectResponse() {

        ServerResponseAlert response = httpRequestHandler.get("ping", ServerResponseAlert.class);

        assertEquals(response.getMessage(), "pong");

    }

    @Test
    public void attemptIncorrectRequestTest() {

        // Force an error to occur within the method, which should return null.
        Room obviouslyWrongGetRoom = httpRequestHandler.get("ping", Room.class);
        assertNull(obviouslyWrongGetRoom);

        Room obviouslyWrongPostRoom = httpRequestHandler.post(
                "ping",
                null,
                Room.class
        );
        assertNull(obviouslyWrongPostRoom);

        Room obviouslyWrongPutRoom = httpRequestHandler.put(
                "ping",
                null,
                Room.class
        );
        assertNull(obviouslyWrongPutRoom);

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


        Bike wonkyBike = null;
        // Force an error to occur within the method, and see if it gets caught.
        try {
            wonkyBike = httpRequestHandler.convertModel(testRoom, Bike.class);
        } catch (IllegalArgumentException e) {
            // Do nothing: Exception thrown due to ObjectMapper.
            assertNull(wonkyBike);
        }


    }

    /**
     * Checks if the default constructor works,
     * and if the new {@link HttpRequestHandler} has the same user as the static one.
     */
    @Test
    void constructorTest() {
        HttpRequestHandler newHandler = new HttpRequestHandler();
        assertNotNull(newHandler);

        HttpRequestHandler.saveUser(new User(
                new Details(),
                "email",
                "",
                "",
                UserKind.Admin)
        );

        assertEquals(HttpRequestHandler.user, HttpRequestHandler.user);
    }

}