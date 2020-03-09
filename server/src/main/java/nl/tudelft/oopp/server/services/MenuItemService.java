package nl.tudelft.oopp.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import nl.tudelft.oopp.server.models.MenuItem;
import nl.tudelft.oopp.server.repositories.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    /**
     * Gets a list of all menu items.
     */
    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItemRepository.findAll().forEach(menuItems::add);
        return menuItems;
    }

    /**
     * Gets a menu item.
     *
     * @param id linked to a menu item
     * @return the menu item with that id or null if it does not exist
     */
    public Optional<MenuItem> getMenuItem(Long id) {
        return menuItemRepository.findById(id);
    }

    /**
     * Adds a menu item.
     * 
     * @param menuItem to be added to the menu items list
     */
    public void addMenuItem(MenuItem menuItem) {
        menuItemRepository.save(menuItem);
    }

    /**
     * Updates a menu item.
     * 
     * @param id       to be linked to a menuItem
     * @param menuItem to be updated
     */
    public void updateMenuItem(Long id, MenuItem menuItem) {
        menuItemRepository.save(menuItem);
    }

    /**
     * Deletes a menu item.
     *
     * @param id linked to a menu item to be deleted
     */
    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }
}
