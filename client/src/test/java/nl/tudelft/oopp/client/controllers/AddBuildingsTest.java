package nl.tudelft.oopp.client.controllers;

import javafx.scene.control.Alert;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddBuildingsTest {

    HttpRequestHandler mockHttpRequestHandler;
    AlertsController mockAlertController;
    AddBuildingsController controller;

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

        when(mockHttpRequestHandler.put(ArgumentMatchers.eq("buildings/admin/add"),
                ArgumentMatchers.any(ClientRequest.class),
                ArgumentMatchers.eq(ServerResponseAlert.class)))
                .thenReturn(
                        new ServerResponseAlert(
                                "Message retrieved from the backend",
                                "INFORMATION"
                        )
                );
        HttpRequestHandler.saveUser(
                new User(
                        new Details(),
                        "admin@admin.tudelft.nl",
                        "admin",
                        "pass",
                        UserKind.Admin
                )
        );

        controller = new AddBuildingsController(mockAlertController);
        controller.httpRequestHandler = mockHttpRequestHandler;
    }

    @Test
    void testCorrectResponse() {
        controller.addBuilding(
                "buildingName",
                42L,
                "beautiful description",
                8,
                17
        );
        Mockito.verify(mockAlertController).show(
                Alert.AlertType.INFORMATION,
                "Message retrieved from the backend"
        );
    }
}
