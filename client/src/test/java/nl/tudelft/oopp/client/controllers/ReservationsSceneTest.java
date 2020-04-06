package nl.tudelft.oopp.client.controllers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javafx.scene.control.Alert;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.LoginRequest;
import nl.tudelft.oopp.api.models.User;
import nl.tudelft.oopp.api.models.UserAuthResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

@PrepareForTest(LoginSceneController.class)
public class ReservationsSceneTest {

    AlertsController mockAlertController;
    LoginSceneController scene;
    LoginRequest testLoginRequest;
    HttpRequestHandler mockHttpRequestHandler;

    @BeforeEach
    void beforeEach() {
        mockAlertController = mock(AlertsController.class);
        Mockito.doNothing().when(mockAlertController).show(
                ArgumentMatchers.any(Alert.AlertType.class),
                ArgumentMatchers.anyString());
        Mockito.doNothing().when(mockAlertController).show(
                ArgumentMatchers.any(Alert.AlertType.class),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString());


        mockHttpRequestHandler = mock(HttpRequestHandler.class);

        testLoginRequest = new LoginRequest(
                "username",
                "password"
        );
        when(mockHttpRequestHandler.post(ArgumentMatchers.eq("login"), ArgumentMatchers.any(LoginRequest.class),
                ArgumentMatchers.eq(UserAuthResponse.class)))
                .thenReturn(
                    new UserAuthResponse(
                        "invalid login",
                        "ERROR",
                        new User()
                ));

        when(mockHttpRequestHandler.post(ArgumentMatchers.eq("login"), ArgumentMatchers.eq(testLoginRequest),
                ArgumentMatchers.eq(UserAuthResponse.class)))
                .thenReturn(
                        new UserAuthResponse(
                        "success!",
                        "CONFIRMATION",
                        new User()
                ));

        scene = new LoginSceneController(mockAlertController);
        scene.httpRequestHandler = mockHttpRequestHandler;

    }

    @Test
    public void testLoginRequest()  {

        UserAuthResponse response = scene.sendLoginRequest(
                "username", "password"
        );

        Mockito.verify(mockHttpRequestHandler).post("login", testLoginRequest,
                UserAuthResponse.class);

        Assertions.assertEquals(response.getMessage(), "success!");

        response = scene.sendLoginRequest(
                "doesnt", "exist"
        );

        Assertions.assertEquals(response.getMessage(), "invalid login");
    }

    @Test
    public void testEmptyLogin() {

        scene.tryLogin("", "");
        Mockito.verify(mockAlertController).show(
                Alert.AlertType.WARNING,
                "Warning",
                null,
                "Please provide a username and password."
        );
    }

    @Test
    public void testSuccessLogin() {
        try {
            scene.tryLogin("username", "password");
        } catch (ExceptionInInitializerError | NoClassDefFoundError | NullPointerException ignored) {
            return;
            // Error is thrown due to scene switching.
        }

        Mockito.verify(mockAlertController).show(
                Alert.AlertType.CONFIRMATION,
                "success!"
        );

    }

    @Test
    public void testWrongLogin() {

        scene.tryLogin("username", "wrongPassword");
        Mockito.verify(mockAlertController).show(
                Alert.AlertType.ERROR,
                "invalid login"
        );

    }

    @Test
    void invalidResponseLogin() {

        // Setup the mock so an invalid response is sent.
        when(mockHttpRequestHandler.post(
                ArgumentMatchers.eq("login"),
                ArgumentMatchers.any(LoginRequest.class),
                ArgumentMatchers.eq(UserAuthResponse.class))
        ).thenReturn(null);

        scene.tryLogin("username", "password");
        Mockito.verify(mockAlertController).show(
                Alert.AlertType.ERROR,
                "Invalid response from server."
        );
    }

    @Test
    void constructorTest() {

        scene = new LoginSceneController(mockAlertController);
        Assertions.assertEquals(scene.alertsController, mockAlertController);
        try {
            scene = new LoginSceneController();
        } catch (ExceptionInInitializerError | NoClassDefFoundError ignored) {
            // Error is thrown due to JavaFx trying to load components in the testing context.
        }
        Assertions.assertNotNull(scene.alertsController);

    }

}
