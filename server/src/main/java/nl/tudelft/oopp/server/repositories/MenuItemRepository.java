package nl.tudelft.oopp.server.repositories;

import nl.tudelft.oopp.server.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * interface for menu item to implement crud operations.
 */
@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
