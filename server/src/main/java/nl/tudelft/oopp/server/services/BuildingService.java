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

    /** Gets all the buildings numbers and existing names in the database.
     * @return A {@link ListPair} object containing two lists - the numbers and the names.
     */
    public ListPair<Long, String> getBuildingNumbersAndNames() {
        logger.info("Fetching the numbers of all buildings ...");
        List<BuildingNumber> queriedNumbers = buildingRepository.getAllBy();
        logger.info("Fetching all names from the database table details ...");
        List<DetailsName> queriedNames = detailsRepository.findAllBy();

        List<Long> sentNumbers = new ArrayList<>();
        List<String> sentNames = new ArrayList<>();

        for (BuildingNumber number: queriedNumbers) {
            sentNumbers.add(number.getNumber());
        }
        for (DetailsName name: queriedNames) {
            sentNames.add(name.getName());
        }
        logger.info("Constructing of ListPair object successful. ");
        return new ListPair<>(sentNumbers, sentNames);
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
        logger.info("No existing name or building with number " + building.number + " found.");
        buildingRepository.save(building);

    }

    /**
     * Updates a building by using the generic method updateBuilding() to set new details for it.
     *
     * @param number     The number/id of the building to update.
     * @param newDetails The new details to set on the building.
     */
    public void updateBuildingDetails(Long number, Details newDetails) {
        updateBuilding(number, newDetails);
    }

    /**
     * Updates a building by using the generic method updateBuilding() to set new opening hours
     * for it.
     *
     * @param number          The number/id of the building to change.
     * @param newOpeningHours The new opening hours to set on the building.
     */
    public void updateBuildingOpeningHours(Long number, TimeSlot newOpeningHours) {
        updateBuilding(number, newOpeningHours);
    }


    /**
     * Deletes the building with the specified number from the database.
     *
     * @param number Number of the building to be deleted from the database.
     */
    public void delete(Long number) throws EntityNotFoundException {
        logger.info("Checking whether building " + number + " still exists ...");
        if (!buildingRepository.existsByNumber(number)) {
            logger.error("Building " + number + " was not found.");
            throw new EntityNotFoundException();
        } else {
            logger.info("Building " + number + " found. Deleting ...");
            buildingRepository.deleteById(number);
        }
    }


    /**
     * Generic method to update a building in the database with the given number to
     * have the given field value.
     *
     * @param number           The number of the building to update.
     * @param fieldToBeChanged The new details to be set on te building.
     * @throws EntityNotFoundException Throws it if the searched building does not exist.
     */
    public <T> void updateBuilding(Long number, T fieldToBeChanged)
        throws EntityNotFoundException {

        Optional<Building> optional = getBuilding(number);
        if (optional.isEmpty()) {
            logger.info("Building " + number + " not found. Cannot be updated.");
            throw new EntityNotFoundException();
        } else {
            Building building = optional.get();
            if (fieldToBeChanged instanceof TimeSlot) {
                logger.info("Updating the opening hours of building " + number + " ...");
                building.openingHours = (TimeSlot) fieldToBeChanged;
            } else {
                logger.info("Updating the details of building " + number + " ...");
                building.details = (Details) fieldToBeChanged;
            }
            buildingRepository.save(building);
        }

    }


}
