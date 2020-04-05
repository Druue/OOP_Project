package nl.tudelft.oopp.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
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
import nl.tudelft.oopp.server.services.RoomFilteringService;
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
        UserService.class, AuthorizationService.class, RoomFilteringService.class, BuildingService.class})
@SpringBootTest
class ReservableControllerTest {

    Timestamp openingTime;
    Timestamp closingTime;
    TimeSlot timeSlot;
    Details details;
    Reservable reservable1;
    Reservable reservable2;
    Reservable reservable3;
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
    RoomFilteringService roomFilteringService;

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
        details = new Details("EECMS",
                "This is the faculty of computer science, mathematics and electrical engineering", "EECMS.png");
        reservables.add(reservable1 = new Bike(456L, details));
        reservables.add(reservable2 = new Room());
        reservables.add(reservable3 = new Bike(78L, details));
    }

    @Test
    public void getAllReservablesTest() throws Exception {
        given(reservableService.getAllReservables()).willReturn(reservables);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/reservables/all/rooms")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.roomList").exists());

        List<Reservable> expected = reservableService.getAllReservables();

        assertEquals(expected, reservables);

    }

    @Test
    public void getAllReservablesOfBuildingTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/reservables/admin/all/type/building")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.roomList[*]").exists());
        List<Reservable> expected = reservableService.getAllReservablesForBuilding(36L, "Bike");

        assertEquals(expected, reservables);
    }

    @Test
    public void getAllRoomsByFilterCapacityTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/user/filter/capacity/group")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        List<Room> expected = roomFilteringService.findAllByCapacity("big", 6);

        assertEquals(expected, reservables);
    }

    @Test
    public void addNewReservableTest() throws Exception {

        Reservable reservable4 = new Bike(28L, details);
        mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(reservable4);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/user/filter/capacity/group")
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("building", "http:/localhost:8080/buildings/"
                        + "user/filter/capacity/group"))
                .andExpect(status().isOk());


        when(reservableService.getReservable(28L)).thenReturn(java.util.Optional.of(reservable4));

        doNothing().when(reservableService).addReservable(reservable4, 28L);

        verify(reservableService, times(1)).getReservable(28L);
        verify(reservableService, times(1)).addReservable(reservable4, 28L);
        verifyNoMoreInteractions(reservableService);

    }

    @Test
    public void updateReservableTest() throws Exception {
        reservable1 = new Bike(21L, details);

        when(reservableService.getReservable(21L)).thenReturn(java.util.Optional.ofNullable(reservable1));

        doNothing().when(reservableService).updateReservable(24L, reservable1);

        mapper = new ObjectMapper();

        String json2 = mapper.writeValueAsString(reservable1);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/reservables/id", reservable1.getId())
                .content(json2)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(reservableService, times(1)).getReservable(21L);
        verify(reservableService, times(1)).updateReservable(24L, reservable1);
        verifyNoInteractions(reservableService);

    }

    @Test
    public void deleteReservableTest() throws Exception {
        reservable2 = new Bike(36L, details);

        when(reservableService.getReservable(36L)).thenReturn(java.util.Optional.ofNullable(reservable2));

        doNothing().when(reservableService).deleteReservable(36L);

        mapper = new ObjectMapper();

        String json3 = mapper.writeValueAsString(reservable2);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/reservables/id", reservable1.getId())
                .content(json3)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(reservableService, times(1)).getReservable(36L);
        verify(reservableService, times(1)).deleteReservable(36L);
        verifyNoInteractions(reservableService);


    }



}