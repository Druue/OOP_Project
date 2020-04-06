package nl.tudelft.oopp.server;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.api.models.UserKind;
import nl.tudelft.oopp.server.controllers.BuildingRequestController;
import nl.tudelft.oopp.server.models.Bike;
import nl.tudelft.oopp.server.models.Building;
import nl.tudelft.oopp.server.models.Details;
import nl.tudelft.oopp.server.models.Reservable;
import nl.tudelft.oopp.server.models.TimeSlot;
import nl.tudelft.oopp.server.repositories.BuildingRepository;
import nl.tudelft.oopp.server.repositories.BuildingsDetails;
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
import org.springframework.context.i18n.SimpleTimeZoneAwareLocaleContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;





@AutoConfigureMockMvc
@ContextConfiguration(classes = {BuildingService.class, BuildingRequestController.class, AuthorizationService.class,
        UserService.class})
@SpringBootTest
class BuildingRequestControllerTest {

    /**
     * This is allows us to create a mock and use the CRUD
     * methods from the building service class.
     */
    @Mock
    BuildingService buildingServiceMock;

    @Mock
    UserService userService;

    @Mock
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
        details = new Details("EECMS",
                "This is the faculty of computer science, mathematics and electrical engineering", "EECMS.png");
        reservable = new Bike(456L, details);
        reservableList = new ArrayList<>();
        reservableList.add(reservable);
        mockBuilding1 = new Building(36L, details, timeSlot, reservableList);
        mockBuilding2 = new Building(28L, details,  timeSlot, reservableList);

    }

    @Test
    public void sendAllBuildingsInfoSuccess() throws Exception {
        List<Building> buildings = Arrays.asList(mockBuilding1, mockBuilding2);
        when(buildingServiceMock.getAllBuildings()).thenReturn(buildings);
        mockMvc.perform(get("/buildings/user/all/information")
                .accept(MediaType.ALL))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));


    }

    @Test
    public void addBuildingTest() throws Exception {

        Building building = new Building(36L, details, timeSlot);


        doNothing().when(buildingServiceMock).addBuilding(building);


        mapper = new ObjectMapper();

        ClientRequest<Building> request = new ClientRequest<>("admin", UserKind.Admin, building);


        String json = mapper.writeValueAsString(request);


        doNothing().when(authorizationService).checkAuthorization(any());

        mockMvc.perform(put("/buildings/admin/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk()).andReturn();


        //verify(buildingServiceMock, times(1)).addBuilding(building);
        // This is commented out for now but will be looked at later
        verifyNoMoreInteractions(buildingServiceMock);

    }

    @Test
    public void deleteBuildingTest() throws Exception {
        Building building = new Building(21L, details, timeSlot);
        when(buildingServiceMock.getBuilding(21L)).thenReturn(java.util.Optional.of(building));

        doNothing().when(buildingServiceMock).delete(21L);

        mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(building);

        mockMvc.perform(delete("/buildings/admin/delete")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk()).andReturn();

        verify(buildingServiceMock, times(1)).getBuilding(21L);
        verify(buildingServiceMock, times(1)).delete(21L);
        verifyNoMoreInteractions(buildingServiceMock);
    }



}

