package nl.tudelft.oopp.server;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nl.tudelft.oopp.server.controllers.BuildingRequestController;
import nl.tudelft.oopp.server.models.Bike;
import nl.tudelft.oopp.server.models.Building;
import nl.tudelft.oopp.server.models.Details;
import nl.tudelft.oopp.server.models.Reservable;
import nl.tudelft.oopp.server.models.TimeSlot;
import nl.tudelft.oopp.server.repositories.BuildingRepository;
import nl.tudelft.oopp.server.repositories.DetailsRepository;
import nl.tudelft.oopp.server.repositories.UserRepository;
import nl.tudelft.oopp.server.services.AuthorizationService;
import nl.tudelft.oopp.server.services.BuildingService;
import nl.tudelft.oopp.server.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
@ContextConfiguration(classes = {BuildingService.class, BuildingRequestController.class, AuthorizationService.class})
@SpringBootTest
class BuildingRequestControllerTest {

    /**
     * This is allows us to create a mock and use the CRUD
     * methods from the building service class.
     */
    @Mock
    BuildingService buildingServiceMock;

    @MockBean
    UserService userService;

    @MockBean
    AuthorizationService authorizationService;


    ObjectMapper mapper;

    Timestamp openingTime;
    Timestamp closingTime;
    TimeSlot timeSlot;
    Details details;
    Reservable reservable;
    List<Reservable> reservableList;
    Building mockBuilding1;
    Building mockBuilding2;

    @MockBean
    BuildingRepository buildingRepository;

    @MockBean
    DetailsRepository detailsRepository;

    @MockBean
    UserRepository userRepository;

    /**
     * This is the main entry point for server side tests
     * it will perform a request and return a type that
     * allows for chain reactions.
     */
    @Autowired
    private MockMvc mockMvc;
    /**
     * This is injects mock data into tested objects.
     */
    @InjectMocks
    private BuildingRequestController buildingRequestController;

    /**
     * This is the setup for all of the mock MvC.
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(buildingRequestController)
                .build();

        openingTime = Timestamp.valueOf("2020-02-04 09:30:00");
        closingTime = Timestamp.valueOf("2020-04-09 22:30:00");
        timeSlot = new TimeSlot(openingTime, closingTime);
        details = new Details(45L,"EECMS",
                "This is the faculty of computer science, mathematics and electrical engineering", "EECMS.png");
        reservable = new Bike(456L, details);
        List<Reservable> reservablesList = new ArrayList<>();
        reservablesList.add(reservable);
        mockBuilding1 = new Building(36L, details, timeSlot, reservablesList);
        mockBuilding2 = new Building(28L, details,  timeSlot, reservablesList);

    }

    @Test
    void sendAllBuildingsSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/buildings/user/all")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.buildingList").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isNotEmpty());

    }


    @Test
    public void sendAllBuildingsFail() throws Exception {
        List<Building> buildings = Arrays.asList(
                mockBuilding2,
                mockBuilding1);

        when(buildingServiceMock.getAllBuildings()).thenReturn(buildings);
        mockMvc.perform(get("buildings/user/all"))
                .andExpect(status().is(404));
        Mockito.verifyNoMoreInteractions(buildingServiceMock);

    }

    @Test
    public void sendAllBuildingsInfoSuccess() throws Exception {
        List<Building> buildings = Arrays.asList(
                mockBuilding2,
                mockBuilding1);
        when(buildingServiceMock.getAllBuildings()).thenReturn(buildings);
        mockMvc.perform(get("/buildings/user/all/information")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));


    }

    @Test
    public void sendAllBuildingsNumbersAndNamesTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .get("/buildings/admin/all/uniquevalues")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(MockMvcResultMatchers.jsonPath("$.[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0]").isNotEmpty());
    }

    @Test
    public void addBuildingTest() throws Exception {

        Building building = new Building(36L, details, timeSlot);

        when(buildingServiceMock.getBuilding(36L)).thenReturn(java.util.Optional.of(building));

        doNothing().when(buildingServiceMock).addBuilding(building);

        mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(building);

        mockMvc.perform(MockMvcRequestBuilders.post("/buildings/admin/add")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("building", "http:/localhost:8080/buildings/admin/add"));

        verify(buildingServiceMock, times(1)).getBuilding(28L);
        verify(buildingServiceMock, times(1)).addBuilding(building);
        verifyNoMoreInteractions(buildingServiceMock);

    }

}

