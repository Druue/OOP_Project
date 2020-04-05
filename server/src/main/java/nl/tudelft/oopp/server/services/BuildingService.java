package nl.tudelft.oopp.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.management.InstanceAlreadyExistsException;
import javax.persistence.EntityNotFoundException;
import nl.tudelft.oopp.api.models.ListPair;
import nl.tudelft.oopp.server.models.Building;
import nl.tudelft.oopp.server.models.Details;
import nl.tudelft.oopp.server.models.TimeSlot;
import nl.tudelft.oopp.server.repositories.BuildingNumber;
import nl.tudelft.oopp.server.repositories.BuildingRepository;
import nl.tudelft.oopp.server.repositories.BuildingsDetails;
import nl.tudelft.oopp.server.repositories.DetailsName;
import nl.tudelft.oopp.server.repositories.DetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {

    Logger logger = LoggerFactory.getLogger(BuildingService.class);
    private final BuildingRepository buildingRepository;
    private final DetailsRepository detailsRepository;

    public BuildingService(BuildingRepository buildingRepository,
                           DetailsRepository detailsRepository) {
        this.buildingRepository = buildingRepository;
        this.detailsRepository = detailsRepository;
    }

    /**
     * Gets all buildings.
     *
     * @return a list of all buildings
     */
    public List<Building> getAllBuildings() {
        logger.info("Fetching all buildings from the database ...");
        return buildingRepository.findAll();
    }

    /**
     * Use {@link BuildingRepository} bean to get the details of all
     * buildings.
     *
     * @return A list of {@link BuildingsDetails} objects that contain the
     *      number, name, description, image, and opening hours.
     */
    public List<BuildingsDetails> getBuildingsDetails() {
        logger.info("Fetching the numbers, names, and opening hours of all buildings ...");
        return buildingRepository.findAllBy();
    }

    /**
     * Gets a building.
     *
     * @param id we search for
     * @return the building with that exact id if it exists or null if not
     */
    public Optional<Building> getBuilding(Long id) {
        logger.info("Searching and fetching for building " + id);
        return buildingRepository.findById(id);
    }


    /**
     * Adds a new building to the database.
     *
     * @param building The building to add to the database.
     * @throws InstanceAlreadyExistsException Throws it if a building already exists with the
     *                                          given number or if the details name already exists.
     */
    public void addBuilding(Building building) throws InstanceAlreadyExistsException {
        logger.info("Checking existence of building with number " + building.getNumber()
                + " and details with name " + building.getDetails().getName());
        if (buildingRepository.existsByNumber(building.getNumber())
            || detailsRepository.existsByName(building.getDetails().getName())) {
            logger.info("Found building with number " + building.getNumber()
                + " or details with name " + building.getDetails().getName());
            throw new InstanceAlreadyExistsException();
        }
        logger.info("No existing name or building with number " + building.getNumber() + " found.");
        buildingRepository.save(building);

    }



}
