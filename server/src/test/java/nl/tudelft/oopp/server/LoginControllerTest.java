package nl.tudelft.oopp.server;

import nl.tudelft.oopp.api.models.LoginRequest;
import nl.tudelft.oopp.api.models.UserAuthResponse;
import nl.tudelft.oopp.server.controllers.LoginController;
import nl.tudelft.oopp.server.models.Details;
import nl.tudelft.oopp.server.models.User;
import nl.tudelft.oopp.server.models.UserKind;
import nl.tudelft.oopp.server.repositories.UserRepository;
import nl.tudelft.oopp.server.services.LoggerService;
import nl.tudelft.oopp.server.services.LoginService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {LoginController.class, UserService.class, LoginService.class})
@SpringBootTest
public class LoginControllerTest {

    User user;
    User otherUser;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserRepository userRepository;

    @Mock
    LoginService loginService;

    @Mock
    LoggerService loggerService;

    @Mock
    UserService userService;

    @InjectMocks
    LoginController loginController;

    @BeforeEach
    public void beforeEach() {


        when(userService.getUserUserName(any())).thenReturn(
                new User(
                        42L,
                        "user_one@mail.com",
                        "user_one",
                        "pass123",
                        new Details(),
                        UserKind.Student
                )
        );

        when(loginService.getUserInformation(any())).thenReturn(
                new User(
                        42L,
                        "user_one@mail.com",
                        "user_one",
                        "pass123",
                        new Details(),
                        UserKind.Student
                )
        );

        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();

        when(userService.getUserUserName(eq("user_two"))).thenReturn(
                new User(
                        42L,
                        "user_two@mail.com",
                        "user_two",
                        "badPassword",
                        new Details(),
                        UserKind.Student
                )
        );

        when(userService.getUserUserName(eq("missing_user"))).thenReturn(
                null
        );
    }


    @Test
    public void validateAuthExistingUserTest() {

        ResponseEntity<UserAuthResponse> response = loginController.validateAuthentication(
                new LoginRequest(
                        "user_one",
                        "pass123"
                )
        );

        assertEquals(response.getBody().getMessage(), "Successful login!");
    }


}
