package nl.tudelft.oopp.server.repositories;

import nl.tudelft.oopp.server.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * interface for room to implement crud operations.
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
