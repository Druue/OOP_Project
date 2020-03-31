package nl.tudelft.oopp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.api.models.Details;
import nl.tudelft.oopp.api.models.LoginRequest;
import nl.tudelft.oopp.api.models.User;
import nl.tudelft.oopp.api.models.UserKind;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RequestsTest {

    User testUser;
    ClientRequest<Integer> testRequest;
    ClientRequest<Integer> identicalRequest;
    ClientRequest<Integer> otherRequest;

    LoginRequest testLoginRequest;

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

        testRequest = new ClientRequest<>(
                testUser.getUsername(),
                testUser.getUserKind(),
                42

        );

        identicalRequest = new ClientRequest<>(
                testUser.getUsername(),
                testUser.getUserKind(),
                42

        );

        otherRequest = new ClientRequest<>(
                testUser.getUsername(),
                testUser.getUserKind(),
                600
        );


        testLoginRequest = new LoginRequest(
                 testUser.getUsername(),
                 testUser.getPassword()
        );


    }

    @Test
    void testEmptyConstructor() {

        ClientRequest<Integer> emptyRequest = new ClientRequest<>();
        assertNull(emptyRequest.getUsername());
        assertNull(emptyRequest.getRole());
        assertNull(emptyRequest.getBody());

        LoginRequest emptyLoginRequest = new LoginRequest();
        assertNull(emptyLoginRequest.getUsername());
        assertNull(emptyLoginRequest.getPassword());

    }

    @Test
    void testGetterSetters() {

        testRequest.setRole(UserKind.Employee);
        assertEquals(testRequest.getRole(), UserKind.Employee);

        testRequest.setUsername("employee");
        assertEquals(testRequest.getUsername(), "employee");

        testRequest.setBody(80);
        assertEquals(testRequest.getBody(), 80);

        testLoginRequest.setUsername("admin");
        assertEquals(testLoginRequest.getUsername(), "admin");

        testLoginRequest.setPassword("badpass");
        assertEquals(testLoginRequest.getPassword(), "badpass");

    }
}
