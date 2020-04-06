package nl.tudelft.oopp.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import javassist.compiler.ast.Symbol;
import nl.tudelft.oopp.api.models.LoginRequest;
import nl.tudelft.oopp.api.models.UserAuthResponse;
import nl.tudelft.oopp.server.controllers.LoginController;
import nl.tudelft.oopp.server.controllers.RegistrationController;
import nl.tudelft.oopp.server.models.Details;
import nl.tudelft.oopp.server.models.User;
import nl.tudelft.oopp.server.models.UserKind;
import nl.tudelft.oopp.server.repositories.UserRepository;
import nl.tudelft.oopp.server.services.LoggerService;
import nl.tudelft.oopp.server.services.LoginService;
import nl.tudelft.oopp.server.services.RegistrationService;
import nl.tudelft.oopp.server.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@AutoConfigureMockMvc
@ContextConfiguration(classes = {RegistrationController.class, UserService.class, RegistrationService.class})
@SpringBootTest
public class RegistrationControllerTest {

    User user;
    User userSameEmail;
    User otherUser;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserRepository userRepository;

    @Mock
    RegistrationService registrationService;

    @Mock
    UserService userService;

    @InjectMocks
    RegistrationController registrationController;

    /**
     * to add users every time before a method.
     */
    @BeforeEach
    public void beforeEach() {

        when(userService.getUserByEmail(eq("existinguser@email.com"))).thenReturn(
                new User(
                        44L,
                        "user_one@mail.com",
                        "user_one",
                        "pass123",
                        new Details(),
                        UserKind.Student
                )
        );

        when(userService.getUserUserName(eq("user"))).thenReturn(
                new User(
                        44L,
                        "user_one@mail.com",
                        "user_one",
                        "pass123",
                        new Details(),
                        UserKind.Student
                )
        );

        user = new User(
                44L,
                "user_one@mail.com",
                "user_one",
                "pass123",
                new Details(),
                UserKind.Student
        );

        userSameEmail = new User(
                52L,
                "user_one@mail.com",
                "user_sameEmail",
                "pass123",
                new Details(),
                UserKind.Student
        );

        otherUser = new User(
                52L,
                "other_User@mail.com",
                "otherUser",
                "pass1241",
                new Details(),
                UserKind.Student
        );
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();

    }

    @Test
    public void validateNewRegisteringUser() {
        ResponseEntity<UserAuthResponse> response = registrationController.registerUser(user);
        assertEquals(response.getBody().getMessage(), "You've registered successfully!");
    }

    @Test
    public void validateAlreadyRegisteredUserEmail() {
        userSameEmail.email = "existinguser@email.com";
        ResponseEntity<UserAuthResponse> response = registrationController.registerUser(user);
        ResponseEntity<UserAuthResponse> secondResponse = registrationController.registerUser(userSameEmail);
        assertEquals(secondResponse.getBody().getMessage(), "User already exists with this email");
    }

    @Test
    public void validateAlreadyRegisteredUserUsername() {
        otherUser.username = "user";
        ResponseEntity<UserAuthResponse> response = registrationController.registerUser(user);
        ResponseEntity<UserAuthResponse> otherResponse = registrationController.registerUser(otherUser);
        assertEquals(otherResponse.getBody().getMessage(), "User already exists with this username");
    }


}
