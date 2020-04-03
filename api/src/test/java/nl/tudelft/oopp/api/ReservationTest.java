package nl.tudelft.oopp.api;

import java.sql.Timestamp;
import nl.tudelft.oopp.api.models.Details;
import nl.tudelft.oopp.api.models.Reservation;
import nl.tudelft.oopp.api.models.Room;
import nl.tudelft.oopp.api.models.TimeSlot;
import nl.tudelft.oopp.api.models.User;
import nl.tudelft.oopp.api.models.UserKind;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReservationTest {

    Reservation testReservation;
    Reservation identicalReservation;
    Reservation otherReservation;
    User user;
    User otherUser;
    TimeSlot timeSlot;
    TimeSlot otherTimeSlot;
    Room room;
    Room otherRoom;

    @BeforeEach
    void beforeEach() {
        user = new User(
                new Details(),
                "user@mail.com",
                "user_one",
                "pass",
                UserKind.Student
        );
        otherUser = new User(
                new Details(),
                "usertwo@mail.com",
                "user_two",
                "pass",
                UserKind.Student
        );

        timeSlot = new TimeSlot(
                new Timestamp(1L),
                new Timestamp(2L)
        );

        otherTimeSlot = new TimeSlot(
                new Timestamp(4L),
                new Timestamp(5L)
        );

        room = new Room(
                4L,
                new Details(),
                67,
                true,
                true
        );

        otherRoom = new Room(
                5L,
                new Details(),
                60,
                false,
                true
        );

        testReservation = new Reservation(
                42L,
                user,
                room,
                timeSlot
        );

        identicalReservation = new Reservation(
                42L,
                user,
                room,
                timeSlot
        );

        otherReservation = new Reservation(
                otherUser,
                room,
                otherTimeSlot
        );
    }

    @Test
    void getterSettersTest() {
        testReservation.setReservationID(2L);
        Assertions.assertEquals(testReservation.getReservationID(), 2L);

        testReservation.setReservable(otherRoom);
        Assertions.assertEquals(testReservation.getReservable(), otherRoom);

        testReservation.setUser(otherUser);
        Assertions.assertEquals(testReservation.getUser(), otherUser);

        testReservation.setTimeslot(otherTimeSlot);
        Assertions.assertEquals(testReservation.getTimeslot(), otherTimeSlot);
    }

    @Test
    void constructorTest() {
        Reservation emptyReservation = new Reservation();
        Assertions.assertNull(emptyReservation.getUser());

        Reservation nearlyEmptyReservation = new Reservation(
                user,
                otherRoom,
                timeSlot
        );

        Assertions.assertNull(nearlyEmptyReservation.getReservationID());
    }

    @Test
    void equalityTest() {
        Assertions.assertEquals(testReservation, identicalReservation);
        Assertions.assertEquals(testReservation, testReservation);

        Assertions.assertNotEquals(testReservation, otherReservation);
        Assertions.assertNotEquals(testReservation, null);
    }
}
