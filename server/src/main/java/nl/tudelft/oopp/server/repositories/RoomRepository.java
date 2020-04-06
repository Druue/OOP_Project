package nl.tudelft.oopp.server.repositories;

import java.util.List;
import nl.tudelft.oopp.server.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * interface for room to implement crud operations.
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAll();

    List<Room> getAllByCapacityGreaterThanEqual(Integer capacity);

    List<Room> getAllByCapacityLessThanEqual(Integer capacity);

    List<Room> getAllByForEmployeeTrue();

    List<Room> getAllByHasProjectorTrue();
}
