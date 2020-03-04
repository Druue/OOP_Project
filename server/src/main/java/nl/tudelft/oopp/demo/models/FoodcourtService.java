package nl.tudelft.oopp.demo.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodcourtService {

    @Autowired
    private FoodCourtRepository foodCourtRepository;

    /**
     *
     * @return a new list of all foodcourts
     */
    public List<Foodcourt> getAllFoodcourts() {
        List<Foodcourt> foodcourts = new ArrayList<Foodcourt>();
        foodCourtRepository.findAll().forEach(foodcourts::add);
        return foodcourts;
    }

    /**
     *
     * @param id linked to a sertain foocourt
     * @return the foodcourt with that id
     */
    public Optional<Foodcourt> getFoodcourt(Integer id) {
        return foodCourtRepository.findById(id);
    }

    /**
     *
     * @param foodcourt to be added to the list of foodcourts
     */
    public void addFoodcourt(Foodcourt foodcourt) {
        foodCourtRepository.save(foodcourt);
    }

    /**
     *
     * @param id new to be assigned to a foodcourt
     * @param foodcourt to be updated
     */
    public void updateFoodcourt(Integer id, Foodcourt foodcourt) {
        foodCourtRepository.save(foodcourt);
    }

    /**
     *
     * @param id linked to a foodcourt to be deleted from the list
     */
    public void deleteFoodcourt(Integer id) {
        foodCourtRepository.deleteById(id);
    }


}
