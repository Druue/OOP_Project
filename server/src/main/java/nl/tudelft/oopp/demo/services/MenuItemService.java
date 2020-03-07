package nl.tudelft.oopp.demo.services;

import nl.tudelft.oopp.demo.models.MenuItem;
import nl.tudelft.oopp.demo.repositories.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItemRepository.findAll().forEach(menuItems::add);
        return menuItems;
    }

    /**
     *
     * @param id linked to a menu item
     * @return the menu item with that id or null if it does not exist
     */
    public Optional<MenuItem> getMenuItem(Long id) {
        return menuItemRepository.findById(id);
    }

    /**
     *
     * @param menuItem to be added to the menu items list
     */
    public void addMenuItem(MenuItem menuItem) {
        menuItemRepository.save(menuItem);
    }

    /**
     *
     * @param id to be linked to a menuItem
     * @param menuItem to be updated
     */
    public void updateMenuItem(Long id, MenuItem menuItem) {
        menuItemRepository.save(menuItem);
    }

    /**
     *
     * @param id linked to a menu item to be deleted
     */
    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }
}
