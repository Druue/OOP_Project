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

    @Query("select res "
        +  "from Reservation res")
    List<Reservation> findAll();

    @Query("select res "
        + " from Reservation res"
        + " where res.timeslot.endTime > ?1")
    List<Reservation> findAllCurrent(Timestamp startSearchTime);

    @Query("select res "
        + " from Reservation res"
        + " WHERE res.timeslot.startTime >= ?1 and res.timeslot.endTime <= ?2 ")
    List<Reservation> findAllReservationsInsideAPeriod(Timestamp start, Timestamp end);

    @Query("select res "
        + " from Reservation res "
        + " where res.user.id=?1 and res.timeslot.endTime > ?2")
    List<Reservation> findAllCurrentByUser(Long userID, Timestamp startSearchTime);

    @Query("SELECT res"
        + " from Reservation res "
        + " WHERE res.user.id=?1 and res.timeslot.startTime >= ?2 and res.timeslot.endTime <= ?3")
    List<Reservation> findAllUserReservationsInsideAPeriod(Long userID,
                                                      Timestamp start,
                                                      Timestamp end);

    @Query("select res "
        + " from Reservation res "
        + " where res.user.id = ?1 and res not in "
        + " (select res from Reservation WHERE res.user.id = ?1 and"
        + " res.timeslot.endTime <= ?2 or res.timeslot.startTime >= ?3 )")
    List<Reservation> findAllForUserForPeriod(Long userID, Timestamp start, Timestamp end);

    @Query("select res "
        + " from Reservation res "
        + " where res.reservable = ?1 and res.timeslot.endTime > ?2")
    List<Reservation> findAllCurrentForReservable(Reservable reservable, Timestamp timestamp);

    @Query("select res "
        + " from Reservation res "
        + " where res.reservable = ?1 and res not in "
        + " (select res from Reservation WHERE res.reservable = ?1 and"
        + " res.timeslot.endTime <= ?2 or res.timeslot.startTime >= ?3 )")
    List<Reservation> findAllForReservableForPeriod(Reservable reservable, Timestamp start,
                                                    Timestamp end);
}
