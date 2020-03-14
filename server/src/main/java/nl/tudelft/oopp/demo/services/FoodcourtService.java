package nl.tudelft.oopp.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import nl.tudelft.oopp.demo.entities.FoodCourt;
import nl.tudelft.oopp.demo.repositories.FoodcourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodcourtService {

    @Autowired
    private FoodcourtRepository foodCourtRepository;

    /**
     * Gets all foodcourts.
     *
     * @return a new list of all foodcourts
     */
    public List<FoodCourt> getAllFoodcourts() {
        List<FoodCourt> foodCourts = new ArrayList<FoodCourt>();
        foodCourtRepository.findAll().forEach(foodCourts::add);
        return foodCourts;
    }

    /**
     * Gets a foodcourt.
     *
     * @param id linked to a sertain foocourt
     * @return the foodcourt with that id
     */
    public Optional<FoodCourt> getFoodcourt(Integer id) {
        return foodCourtRepository.findById(id);
    }

    /**
     * Adds a foodcourt.
     *
     * @param foodcourt to be added to the list of foodcourts
     */
    public void addFoodcourt(FoodCourt foodcourt) {
        foodCourtRepository.save(foodcourt);
    }

    /**
     * Updates a foodcourt.
     *
     * @param id        new to be assigned to a foodcourt
     * @param foodcourt to be updated
     */
    public void updateFoodcourt(Integer id, FoodCourt foodcourt) {
        foodCourtRepository.save(foodcourt);
    }

    /**
     * Deletes a foodcourt.
     *
     * @param id linked to a foodcourt to be deleted from the list
     */
    public void deleteFoodcourt(Integer id) {
        foodCourtRepository.deleteById(id);
    }


}
