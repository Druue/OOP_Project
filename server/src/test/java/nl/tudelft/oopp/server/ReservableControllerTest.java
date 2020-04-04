package nl.tudelft.oopp.server;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import nl.tudelft.oopp.server.controllers.ReservableController;
import nl.tudelft.oopp.server.models.Bike;
import nl.tudelft.oopp.server.models.Details;
import nl.tudelft.oopp.server.models.Reservable;
import nl.tudelft.oopp.server.models.TimeSlot;
import nl.tudelft.oopp.server.repositories.ReservableRepository;
import nl.tudelft.oopp.server.services.ReservableService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;






@AutoConfigureMockMvc
@ContextConfiguration(classes = {ReservableController.class, ReservableService.class, ReservableRepository.class})
@SpringBootTest
public class ReservableControllerTest {

    Timestamp openingTime;
    Timestamp closingTime;
    TimeSlot timeSlot;
    Details details;
    Reservable reservable;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ReservableRepository reservableRepository;

    @MockBean
    ReservableService reservableService;

    @InjectMocks
    private ReservableController reservableController;

    /**
     * This has setup objects for the class.
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(reservableController)
                .build();

        openingTime = Timestamp.valueOf("2020-02-04 09:30:00");
        closingTime = Timestamp.valueOf("2020-04-09 22:30:00");
        timeSlot = new TimeSlot(openingTime, closingTime);
        details = new Details("EECMS",
                "This is the faculty of computer science, mathematics and electrical engineering", "EECMS.png");
        reservable = new Bike(456L, details);

    }

    @Test
    void getAllReservablesSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/reservables/all/rooms")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.roomList").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isNotEmpty());

        Mockito.verifyNoMoreInteractions(reservableService);

    }

    @Test
    public void getAllReservablesOfBuildingTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/reservables/user/all/type/building")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.roomList").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isNotEmpty());

        Mockito.verifyNoMoreInteractions(reservableService);
    }


}
