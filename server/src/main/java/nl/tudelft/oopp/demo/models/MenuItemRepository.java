package nl.tudelft.oopp.demo.models;

import org.springframework.data.repository.CrudRepository;

/**
 * interface for menuItem to to implement crud operations
 */
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
}
