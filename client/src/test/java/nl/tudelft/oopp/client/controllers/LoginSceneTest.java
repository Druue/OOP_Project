package nl.tudelft.oopp.client.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.LoginRequest;
import nl.tudelft.oopp.api.models.User;
import nl.tudelft.oopp.api.models.UserAuthResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

@PrepareForTest(LoginSceneController.class)
public class LoginSceneTest {

    AlertsController mockAlertController;
    LoginSceneController scene;
    LoginRequest testLoginRequest;
    HttpRequestHandler mockHttpRequestHandler;

    @BeforeEach
    void beforeEach() throws IOException, InterruptedException {
        mockAlertController = mock(AlertsController.class);
        Mockito.doNothing().when(mockAlertController).show(any(), any());
        Mockito.doNothing().when(mockAlertController).show(any(), any(), any(), any());

        scene = new LoginSceneController();
        mockHttpRequestHandler = mock(HttpRequestHandler.class);
        scene.httpRequestHandler = mockHttpRequestHandler;

        testLoginRequest = new LoginRequest(
                "username",
                "password"
        );
        when(mockHttpRequestHandler.post(eq("login"), any(LoginRequest.class),
                eq(UserAuthResponse.class)))
                .thenReturn(
                        new UserAuthResponse(
                                "invalid login",
                                "ERROR",
                                new User()
                        ));

        when(mockHttpRequestHandler.post(eq("login"), eq(testLoginRequest),
                eq(UserAuthResponse.class)))
                .thenReturn(
                        new UserAuthResponse(
                        "success!",
                        "CONFIRMATION",
                        new User()
                ));



        scene.alertsController = mockAlertController;

    }

    @Test
    public void testLoginRequest()  {

        UserAuthResponse response = scene.sendLoginRequest(
                "username", "password"
        );

        Mockito.verify(mockHttpRequestHandler).post("login", testLoginRequest,
                UserAuthResponse.class);

        assertEquals(response.getMessage(), "success!");

        response = scene.sendLoginRequest(
                "doesnt", "exist"
        );
        
        assertEquals(response.getMessage(), "invalid login");
    }
}
