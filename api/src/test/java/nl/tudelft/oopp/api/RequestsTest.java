package nl.tudelft.oopp.api;

import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.api.models.Details;
import nl.tudelft.oopp.api.models.LoginRequest;
import nl.tudelft.oopp.api.models.User;
import nl.tudelft.oopp.api.models.UserKind;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RequestsTest {

    User testUser;
    ClientRequest<Integer> testRequest;
    ClientRequest<Integer> identicalRequest;
    ClientRequest<Integer> otherRequest;

    LoginRequest testLoginRequest;
    LoginRequest identicalLoginRequest;
    LoginRequest otherLoginRequest;

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

        identicalLoginRequest = new LoginRequest(
                testUser.getUsername(),
                testUser.getPassword()
        );

        otherLoginRequest = new LoginRequest(
                "other username",
                testUser.getPassword()
        );


    }

    @Test
    void testEmptyConstructor() {

        ClientRequest<Integer> emptyRequest = new ClientRequest<>();
        Assertions.assertNull(emptyRequest.getUsername());
        Assertions.assertNull(emptyRequest.getRole());
        Assertions.assertNull(emptyRequest.getBody());

        LoginRequest emptyLoginRequest = new LoginRequest();
        Assertions.assertNull(emptyLoginRequest.getUsername());
        Assertions.assertNull(emptyLoginRequest.getPassword());

    }

    @Test
    void testGetterSetters() {

        testRequest.setRole(UserKind.Employee);
        Assertions.assertEquals(testRequest.getRole(), UserKind.Employee);

        testRequest.setUsername("employee");
        Assertions.assertEquals(testRequest.getUsername(), "employee");

        testRequest.setBody(80);
        Assertions.assertEquals(testRequest.getBody(), 80);

        testLoginRequest.setUsername("admin");
        Assertions.assertEquals(testLoginRequest.getUsername(), "admin");

        testLoginRequest.setPassword("badpass");
        Assertions.assertEquals(testLoginRequest.getPassword(), "badpass");

    }

    @Test
    void testEquality() {
        Assertions.assertEquals(testLoginRequest, identicalLoginRequest);
        Assertions.assertEquals(testLoginRequest, testLoginRequest);

        Assertions.assertNotEquals(testLoginRequest, otherLoginRequest);
        Assertions.assertNotEquals(testLoginRequest, null);

    }
}
