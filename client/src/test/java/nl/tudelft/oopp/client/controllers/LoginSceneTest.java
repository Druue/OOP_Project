package nl.tudelft.oopp.client.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import nl.tudelft.oopp.api.HttpRequestHandler;
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
    HttpClient mockClient;
    LoginSceneController scene;

    @BeforeEach
    void beforeEach() throws IOException, InterruptedException {
        mockAlertController = mock(AlertsController.class);
        Mockito.doNothing().when(mockAlertController).show(any(), any());
        Mockito.doNothing().when(mockAlertController).show(any(), any(), any(), any());

        scene = new LoginSceneController();
        mockClient = mock(HttpClient.class);



        HttpRequestHandler.setClient(mockClient);



        scene.alertsController = mockAlertController;

    }

    @Test
    public void testLoginRequest() throws IOException, InterruptedException {
        UserAuthResponse response = scene.sendLoginRequest(
                "username", "password"
        );

        Mockito.verify(mockClient).send(any(), any());

        //assertEquals(response.getMessage(), "login sucessfull");

    }
}
