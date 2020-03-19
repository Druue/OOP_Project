package nl.tudelft.oopp.server.services;

import java.util.List;
import java.util.Optional;
import javax.management.InstanceAlreadyExistsException;
import javax.persistence.EntityNotFoundException;
import nl.tudelft.oopp.server.models.Building;
import nl.tudelft.oopp.server.models.Details;
import nl.tudelft.oopp.server.models.TimeSlot;
import nl.tudelft.oopp.server.repositories.BuildingRepository;
import nl.tudelft.oopp.server.repositories.BuildingsDetails;
import nl.tudelft.oopp.server.repositories.DetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {

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
        return buildingRepository.findAllBy();
    }

    /**
     * Gets a building.
     *
     * @param id we search for
     * @return the building with that exact id if it exists or null if not
     */
    public Optional<Building> getBuilding(Long id) {
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
        if (buildingRepository.existsByNumber(building.number)
            || detailsRepository.existsByName(building.details.name)) {
            throw new InstanceAlreadyExistsException();
        }
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
        if (!buildingRepository.existsByNumber(number)) {
            throw new EntityNotFoundException();
        } else {
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
            throw new EntityNotFoundException();
        } else {
            Building building = optional.get();
            if (fieldToBeChanged instanceof TimeSlot) {
                building.openingHours = (TimeSlot) fieldToBeChanged;
            } else {
                building.details = (Details) fieldToBeChanged;
            }
            buildingRepository.save(building);
        }

    }


}
