package nl.tudelft.oopp.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
<<<<<<< HEAD:server/src/main/java/nl/tudelft/oopp/server/services/FoodcourtService.java
import nl.tudelft.oopp.server.models.FoodCourt;
import nl.tudelft.oopp.server.repositories.FoodCourtRepository;
=======
import nl.tudelft.oopp.demo.models.FoodCourt;
import nl.tudelft.oopp.demo.repositories.FoodcourtRepository;
>>>>>>> 59e9244a0240f4f41315ec1e50940aada02337a3:server/src/main/java/nl/tudelft/oopp/demo/services/FoodcourtService.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodCourtService {

    @Autowired
    private FoodCourtRepository foodCourtRepository;

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
