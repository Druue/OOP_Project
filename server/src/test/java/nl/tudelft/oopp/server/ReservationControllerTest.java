package nl.tudelft.oopp.server;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.server.controllers.BuildingRequestController;
import nl.tudelft.oopp.server.controllers.ReservationsController;
import nl.tudelft.oopp.server.models.Reservable;
import nl.tudelft.oopp.server.models.Reservation;
import nl.tudelft.oopp.server.models.Room;
import nl.tudelft.oopp.server.models.TimeSlot;
import nl.tudelft.oopp.server.models.User;
import nl.tudelft.oopp.server.repositories.BuildingRepository;
import nl.tudelft.oopp.server.repositories.DetailsRepository;
import nl.tudelft.oopp.server.repositories.ReservationRepository;
import nl.tudelft.oopp.server.repositories.UserRepository;
import nl.tudelft.oopp.server.services.AuthorizationService;
import nl.tudelft.oopp.server.services.BuildingService;
import nl.tudelft.oopp.server.services.ReservationService;
import nl.tudelft.oopp.server.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {BuildingService.class, ReservationsController.class, ReservationService.class,
        UserService.class, AuthorizationService.class})
@SpringBootTest
public class ReservationControllerTest {

    Reservation dummyReservation1;
    Reservation dummyReservation2;
    List<Reservation> reservations;
    Reservable dummyReservable;
    TimeSlot dummyTimeslot;
    User dummyUser;
    Timestamp start;
    Timestamp end;
    ObjectMapper mapper;

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    ReservationsController reservationsController;

    @Mock
    ReservationService reservationService;

    @Mock
    BuildingService buildingService;

    @Mock
    UserService userService;

    @Mock
    AuthorizationService authorizationService;

    @MockBean
    ReservationRepository reservationRepository;

    @MockBean
    BuildingRepository buildingRepository;

    @MockBean
    DetailsRepository detailsRepository;

    @MockBean
    UserRepository userRepository;

    /**
     * This is the setup for all of the mock MvC.
     */

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(reservationsController).build();
        this.reservations = new ArrayList<>();
        dummyReservable = new Room();
        dummyUser = new User();
        start = Timestamp.valueOf("2020-04-06 20:32:00");
        end = Timestamp.valueOf("2020-04-12 20:32:00");
        dummyTimeslot = new TimeSlot(start, end);
        dummyReservation1 = new Reservation(3L, dummyUser, dummyReservable, dummyTimeslot);
        dummyReservation2 = new Reservation(4L, dummyUser, dummyReservable, dummyTimeslot);
    }

    @Test
    public void getAllReservationsTest() throws Exception {

        reservations.add(dummyReservation1);
        reservations.add(dummyReservation2);
        when(reservationService.getAllReservations()).thenReturn(reservations);
        mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(reservations);
        mockMvc.perform(post("/reservations/admin/all/")
                .accept(MediaType.ALL)
                .content(json))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));

    }


}
