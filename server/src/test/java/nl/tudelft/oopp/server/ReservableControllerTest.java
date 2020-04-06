package nl.tudelft.oopp.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.server.controllers.ReservableController;
import nl.tudelft.oopp.server.models.Bike;
import nl.tudelft.oopp.server.models.Details;
import nl.tudelft.oopp.server.models.Reservable;
import nl.tudelft.oopp.server.models.Room;
import nl.tudelft.oopp.server.models.TimeSlot;
import nl.tudelft.oopp.server.repositories.BuildingRepository;
import nl.tudelft.oopp.server.repositories.DetailsRepository;
import nl.tudelft.oopp.server.repositories.ReservableRepository;
import nl.tudelft.oopp.server.repositories.RoomRepository;
import nl.tudelft.oopp.server.repositories.UserRepository;
import nl.tudelft.oopp.server.services.AuthorizationService;
import nl.tudelft.oopp.server.services.BuildingService;
import nl.tudelft.oopp.server.services.ReservableService;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {ReservableController.class, ReservableService.class,
        UserService.class, AuthorizationService.class, BuildingService.class})
@SpringBootTest
class ReservableControllerTest {

    Timestamp openingTime;
    Timestamp closingTime;
    TimeSlot timeSlot;
    Details details;
    Reservable reservable1;
    Reservable reservable2;
    Reservable reservable3;
    Reservable reservable4;
    Reservable reservable5;
    List<Reservable> reservables;
    ObjectMapper mapper;

    @Autowired
    MockMvc mockMvc;


    @InjectMocks
    ReservableController reservableController;

    @Mock
    ReservableService reservableService;

    @Mock
    UserService userService;

    @Mock
    AuthorizationService authorizationService;

    @Mock
    BuildingService buildingService;

    @MockBean
    BuildingRepository buildingRepository;

    @MockBean
    RoomRepository roomRepository;

    @MockBean
    ReservableRepository reservableRepository;

    @MockBean
    DetailsRepository detailsRepository;

    @MockBean
    UserRepository userRepository;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(reservableController).build();
        this.reservables = new ArrayList<>();
        openingTime = Timestamp.valueOf("2020-02-04 09:30:00");
        closingTime = Timestamp.valueOf("2020-04-09 22:30:00");
        timeSlot = new TimeSlot(openingTime, closingTime);
        details = new Details(45L,"EECMS",
                "This is the faculty of computer science, mathematics and electrical engineering", "EECMS.png");
        reservables.add(reservable1 = new Bike(456L, details));
        reservables.add(reservable2 = new Room());
        reservables.add(reservable3 = new Bike(78L, details));
        reservables.add(reservable4 = new Room());
        reservables.add(reservable5 = new Room());
    }

    @Test
    public void getAllReservablesOfBuildingTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/reservables/user/all/room/building?number=36")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        //List<Reservable> expected = reservableService.getAllReservablesForBuilding(36L, "Bike");
        doNothing().when(reservableService.getAllReservablesForBuilding(36L, "room"));

        verify(reservableService);

    }

    @Test
    public void addNewReservableTest() throws Exception {

        Reservable reservable4 = new Bike(28L, details);
        mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(reservable4);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/reservables/insert/rooms/28")
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(status().isOk());


        when(reservableService.getReservable(28L)).thenReturn(java.util.Optional.of(reservable4));

        doNothing().when(reservableService).addReservable(reservable4, 28L);

        verify(reservableService, times(1)).getReservable(28L);
        verify(reservableService, times(1)).addReservable(reservable4, 28L);
        verifyNoMoreInteractions(reservableService);

    }
}