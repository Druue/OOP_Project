package nl.tudelft.oopp.demo.services;

import nl.tudelft.oopp.demo.models.Building;
import nl.tudelft.oopp.demo.repositories.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BuildingService {

    @Autowired
    private BuildingRepository buidlingRepository;

    /**
     * @return a list of all buildings
     */
    public List<Building>  getAllBuildings() {
        List<Building> Buildings = new ArrayList<Building>();
        buidlingRepository.findAll().forEach(Buildings::add);
        return Buildings;
    }

    /**
     *
     * @param id we search for
     * @return the building with that exact id if it exists or null if not
     */
    public Optional<Building> getBuilding(Integer id) {
        return buidlingRepository.findById(id);
    }

    /**
     * @param building to be added to the list of buildings
     */
    public void addBuilding(Building building) {
        buidlingRepository.save(building);
    }

    /**
     * @param id new one to be updated to
     * @param building to be updated
     */
    public void updateBuilding(Integer id, Building building) {
        buidlingRepository.save(building);
    }

    /**
     *
     * @param id to be deleted from the list of buildings
     */
    public void delete(Integer id) {
        buidlingRepository.deleteById(id);
    }
}
