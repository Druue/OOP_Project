package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.models.MenuItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * interface for menu item to implement crud operations.
 */
@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
}
