package nl.tudelft.oopp.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.tudelft.oopp.demo.controllers.LoginController;
import nl.tudelft.oopp.demo.models.LoginDetails;
import nl.tudelft.oopp.demo.repositories.QuoteRepository;
import nl.tudelft.oopp.demo.services.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;

import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LoginController.class)
public class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    LoginDetails details = new LoginDetails("Mitko" , "123");

    @MockBean
    private QuoteRepository repository;

    @MockBean
    private LoginService service;

    @Autowired
    LoginController loginController;

    @Mock
    HttpSession session;

    @Test
    void testSuccessfulLogin() {

        ResponseEntity responseEntity = loginController.validateAuthentication(details , session);

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(ResponseEntity.status(200).build() , responseEntity);
    }

    @Test
    void testFailingService() throws AuthenticationException {

        given(service.userValidate(details.getNetID() , details.getPassword())).willThrow(AuthenticationException.class);
        loginController = new LoginController(service);

        Assertions.assertThrows(AuthenticationException.class , () -> {
            service.userValidate(details.getNetID() , details.getPassword());
        });

        ResponseEntity responseEntity = loginController.validateAuthentication(details , session);

        Assertions.assertEquals(ResponseEntity.badRequest().header("Reason" , "No such user").build() , responseEntity);
    }

    @Test
    void testParsingJSONIntoJavaObject() {
    }
}
