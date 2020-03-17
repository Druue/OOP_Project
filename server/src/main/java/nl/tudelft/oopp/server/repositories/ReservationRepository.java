package nl.tudelft.oopp.server.repositories;

import java.sql.Timestamp;
import java.util.List;
import nl.tudelft.oopp.server.models.Reservable;
import nl.tudelft.oopp.server.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * interface for reservation to implement crud operations.
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByUser_Id(Long userID);

    List<Reservation> findAll();

    @Query("select res "
        + " from Reservation res"
        + " where res.timeslot.startTime > ?1")
    List<Reservation> findAllCurrent(Timestamp startSearchTime);

    @Query("select res "
        + " from Reservation res "
        + " where res.user.id=?1 and res.timeslot.startTime > ?2")
    List<Reservation> findAllCurrentByUser(Long userID, Timestamp startSearchTime);

    @Query("select res "
        + " from Reservation res "
        + " where res.reservable = ?1 and res.timeslot.startTime > ?2")
    List<Reservation> findAllCurrentReservable(Reservable reservable, Timestamp timestamp);
}
