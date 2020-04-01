package nl.tudelft.oopp.client.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javafx.scene.control.Alert;
import nl.tudelft.oopp.api.HttpRequestHandler;
import nl.tudelft.oopp.api.models.Details;
import nl.tudelft.oopp.api.models.User;
import nl.tudelft.oopp.api.models.UserAuthResponse;
import nl.tudelft.oopp.api.models.UserKind;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;


@PrepareForTest(RegistrationSceneController.class)
public class RegistrationSceneTest {

    AlertsController mockAlertController;
    RegistrationSceneController scene;
    User testUser;
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

        testUser = new User(
                new Details("registered", null, null),
                "user@tudelft.nl",
                "username",
                "password",
                UserKind.Employee
        );

        when(mockHttpRequestHandler.post(eq("register"), ArgumentMatchers.any(User.class),
                eq(UserAuthResponse.class)))
                .thenReturn(
                        new UserAuthResponse(
                                "Registration successful!",
                                "CONFIRMATION",
                                new User()
                        ));

        when(mockHttpRequestHandler.post(eq("register"), eq(testUser), eq(UserAuthResponse.class)))
                .thenReturn(
                        new UserAuthResponse(
                                "User already exists!",
                                "ERROR",
                                new User()
                        )
            );

        scene = new RegistrationSceneController(mockAlertController);
        scene.httpRequestHandler = mockHttpRequestHandler;

    }

    @Test
    void emptyInputTest() {

        scene.attemptRegistration(
                "", "", "", ""
        );

        Mockito.verify(mockAlertController).show(
                Alert.AlertType.WARNING,
                "Warning",
                null,
                "Please fill in all required fields."
        );
    }

    @Test
    void invalidMailTest() {

        scene.attemptRegistration(
                "user", "pass",
                "invalidMailAddress", "name"
        );

        Mockito.verify(mockAlertController).show(
                Alert.AlertType.ERROR,
                "ERROR",
                null,
                "Invalid email address given."
        );

    }

    @Test
    void invalidMailDomainTest() {

        scene.attemptRegistration(
                "user", "pass",
                "invalid@mailadress.com", "name"
        );

        Mockito.verify(mockAlertController).show(
                Alert.AlertType.ERROR,
                "ERROR",
                null,
                "Invalid email address given."
        );
    }

    @Test
    void correctStudentTest() {
        try {
            scene.attemptRegistration(
                    "user", "pass",
                    "employee@student.tudelft.nl", "name"
            );
        } catch (ExceptionInInitializerError | NoClassDefFoundError ignored) {
            return;
            // On successful registration, JavaFx attempts to load components that don't exist
            // Within the testing context.
        }

        Mockito.verify(mockAlertController).show(
                Alert.AlertType.CONFIRMATION,
                "Registration successful!"
        );
    }

    @Test
    void correctEmployeeTest() {
        try {

            scene.attemptRegistration(
                    "user", "pass",
                    "employee@tudelft.nl", "name"
            );
        } catch (ExceptionInInitializerError | NoClassDefFoundError ignored) {
            return;
            // On successful registration, JavaFx attempts to load components that don't exist
            // Within the testing context.
        }

        Mockito.verify(mockAlertController).show(
                Alert.AlertType.CONFIRMATION,
                "Registration successful!"
        );
    }

    @Test
    void correctAdminTest() {

        try {
            scene.attemptRegistration(
                    "asdasd", "pass",
                    "admin@admin.tudelft.nl", "name"
            );
        } catch (ExceptionInInitializerError | NoClassDefFoundError ignored) {
            return;
            // On successful registration, JavaFx attempts to load components that don't exist
            // Within the testing context.
        }

        Mockito.verify(mockAlertController).show(
                Alert.AlertType.CONFIRMATION,
                "Registration successful!"
        );
    }


    @Test
    void registeringExistingUser() {

        scene.attemptRegistration(
                "username", "password",
                "user@tudelft.nl", "registered"
        );
    }

    @Test
    void constructorTest() {

        scene = new RegistrationSceneController(mockAlertController);
        assertEquals(scene.alertsController, mockAlertController);
        try {
            scene = new RegistrationSceneController();
        } catch (ExceptionInInitializerError | NoClassDefFoundError ignored) {
            // Error is thrown due to JavaFx trying to load components in the testing context.
        }
        assertNotNull(scene.alertsController);

    }

    @Test
    void invalidResponseTest() {
        // Setup the mock so an invalid response is sent.
        when(mockHttpRequestHandler.post(
                eq("register"),
                ArgumentMatchers.any(User.class),
                eq(UserAuthResponse.class))
        ).thenReturn(null);

        scene.attemptRegistration("username", "pass",
                "mail@tudelft.nl", "someName");
        Mockito.verify(mockAlertController).show(
                Alert.AlertType.ERROR,
                "Invalid response from server."
        );
    }

}
