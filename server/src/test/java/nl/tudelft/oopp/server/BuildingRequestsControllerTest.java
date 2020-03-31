package nl.tudelft.oopp.server;


import nl.tudelft.oopp.api.models.ClientRequest;
import nl.tudelft.oopp.api.models.ServerResponseAlert;
import nl.tudelft.oopp.server.controllers.BuildingRequestController;
import nl.tudelft.oopp.server.models.Building;
import nl.tudelft.oopp.server.repositories.BuildingNumber;
import nl.tudelft.oopp.server.repositories.BuildingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BuildingRequestController.class)
class BuildingRequestsControllerTest {

    @InjectMocks
    private BuildingRequestController buildingRequestController;

    @Mock
    BuildingRepository buildingRepository;

    @Test
    void addBuildings() {
        MockHttpServletRequest request = new MockHttpServletRequest();

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ClientRequest<Building> building = new ClientRequest<Building>();

        when(buildingRepository.getAllBy()).thenReturn((List<BuildingNumber>) building);


        ResponseEntity<ServerResponseAlert> responseEntity = buildingRequestController.addBuilding(building);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);

    }

}

