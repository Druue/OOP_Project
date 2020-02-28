package nl.tudelft.oopp.demo;

import nl.tudelft.oopp.demo.controllers.LoginController;
import nl.tudelft.oopp.demo.models.LoginDetails;
import nl.tudelft.oopp.demo.services.LoginService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;

import static org.mockito.BDDMockito.given;


public class LoginControllerTest {

    LoginDetails details = new LoginDetails("Mitko" , "123");
    private LoginController loginController;

    private LoginService successService = Mockito.mock(LoginService.class);
    private LoginService failiingService = Mockito.mock(LoginService.class);
    private HttpSession session = Mockito.mock(HttpSession.class);

    @Test
    void testSuccessfulLogin() {

        loginController = new LoginController(successService);

        ResponseEntity responseEntity = loginController.validateAuthentication(details , session);

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(ResponseEntity.status(200).build() , responseEntity);
    }

    @Test
    void testFailiingService() throws AuthenticationException {

        given(failiingService.userValidate(details.getNetID() , details.getPassword())).willThrow(AuthenticationException.class);
        loginController = new LoginController(failiingService);

        Assertions.assertThrows(AuthenticationException.class , () -> {
            failiingService.userValidate(details.getNetID() , details.getPassword());
        });

        ResponseEntity responseEntity = loginController.validateAuthentication(details , session);

        Assertions.assertEquals(ResponseEntity.badRequest().header("Reason" , "No such user").build() , responseEntity);
    }
}
