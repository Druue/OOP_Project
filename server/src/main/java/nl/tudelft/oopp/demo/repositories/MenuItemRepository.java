package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.models.MenuItem;
import org.springframework.data.repository.CrudRepository;

/**
 * interface for menu item to implement crud operations
 */
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
}
