package nl.tudelft.oopp.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import nl.tudelft.oopp.server.models.Foodcourt;
import nl.tudelft.oopp.server.repositories.FoodcourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodcourtService {

    @Autowired
    private FoodcourtRepository foodcourtRepository;

    /**
     * Gets all foodcourts.
     *
     * @return a new list of all foodcourts
     */
    public List<Foodcourt> getAllFoodcourts() {
        List<Foodcourt> foodcourts = new ArrayList<>();
        foodcourtRepository.findAll().forEach(foodcourts::add);
        return foodcourts;
    }

    /**
     * Gets a Foodcourt.
     *
     * @param id linked to a sertain foocourt
     * @return the Foodcourt with that id
     */
    public Optional<Foodcourt> getFoodcourt(Long id) {
        return foodcourtRepository.findById(id);
    }

    /**
     * Adds a Foodcourt.
     *
     * @param foodcourt to be added to the list of Foodcourts.
     */
    public void addFoodcourt(Foodcourt foodcourt) {
        foodcourtRepository.save(foodcourt);
    }

    /**
     * Updates a foodcourt.
     *
     * @param id        new to be assigned to a foodcourt
     * @param foodcourt to be updated
     */
    public void updateFoodcourt(Long id, Foodcourt foodcourt) {
        foodcourtRepository.save(foodcourt);
    }

    /**
     * Deletes a foodcourt.
     *
     * @param id linked to a foodcourt to be deleted from the list
     */
    public void deleteFoodcourt(Long id) {
        foodcourtRepository.deleteById(id);
    }
}
