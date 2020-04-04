package nl.tudelft.oopp.server;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.server.models.Details;
import nl.tudelft.oopp.server.models.Reservation;
import nl.tudelft.oopp.server.models.Room;
import nl.tudelft.oopp.server.models.TimeSlot;
import nl.tudelft.oopp.server.models.User;
import nl.tudelft.oopp.server.models.UserKind;
import nl.tudelft.oopp.server.repositories.ReservationRepository;
import nl.tudelft.oopp.server.services.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class ReservationsServiceTest {
    @InjectMocks
    ReservationService reservationsService;

    @Mock
    ReservationRepository reservationRepository;

    User dummyUser;
    Room dummyReservable;
    Details dummyDetails;
    TimeSlot dummyTimeSlot;
    UserKind dummyKind;
    Timestamp dummyTimeStamp1;
    Timestamp dummyTimestamp2;

    /**
     * This has setup objects for the class.
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        dummyUser = new User(345L, "test@student.tudelft.nl", "teststudent", "test", dummyDetails, dummyKind);
        dummyDetails = new Details("Water", "This is a water room.", "water.png");
        dummyReservable = new Room();
        dummyTimeStamp1 = new Timestamp(2020 - 04 - 03);
        dummyTimestamp2 = new Timestamp(2020 - 04 - 18);
        dummyTimeSlot = new TimeSlot(dummyTimeStamp1, dummyTimestamp2);
    }

    @Test
    public void getAllReservationsTest() {

        List<Reservation> reservations = new ArrayList<>();
        Reservation booking1 = new Reservation(21L, dummyUser, dummyReservable, dummyTimeSlot);
        Reservation booking2 = new Reservation(22L, dummyUser, dummyReservable, dummyTimeSlot);
        Reservation booking3 = new Reservation(23L, dummyUser, dummyReservable, dummyTimeSlot);
        reservations.add(booking1);
        reservations.add(booking2);
        reservations.add(booking3);


        when(reservationRepository.findAll()).thenReturn(reservations);

        List<Reservation> emptyReservations = reservationsService.getAllReservations();

        assertEquals(3, emptyReservations.size());
        verify(reservationRepository, times(1)).findAll();
    }
}
