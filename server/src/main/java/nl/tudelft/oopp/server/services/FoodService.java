package nl.tudelft.oopp.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import nl.tudelft.oopp.server.models.Food;
import nl.tudelft.oopp.server.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    /**
     * Gets a list of all menu items.
     */
    public List<Food> getAllFoodstuffs() {
        List<Food> foodStuffs = new ArrayList<>();
        foodRepository.findAll().forEach(foodStuffs::add);
        return foodStuffs;
    }

    /**
     * Gets a menu item.
     *
     * @param id linked to a menu item
     * @return the menu item with that id or null if it does not exist
     */
    public Optional<Food> getFood(Long id) {
        return foodRepository.findById(id);
    }

    /**
     * Adds a menu item.
     * 
     * @param food to be added to the menu items list
     */
    public void addFood(Food food) {
        foodRepository.save(food);
    }

    /**
     * Updates a menu item.
     * 
     * @param id   to be linked to a menuItem
     * @param food to be updated
     */
    public void updateFood(Long id, Food food) {
        foodRepository.save(food);
    }

    /**
     * Deletes a menu item.
     *
     * @param id linked to a menu item to be deleted
     */
    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }
}
