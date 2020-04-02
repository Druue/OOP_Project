package nl.tudelft.oopp.api;

import nl.tudelft.oopp.api.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(testReservation.getReservationID(), 2L);

        testReservation.setReservable(otherRoom);
        assertEquals(testReservation.getReservable(), otherRoom);

        testReservation.setUser(otherUser);
        assertEquals(testReservation.getUser(), otherUser);

        testReservation.setTimeslot(otherTimeSlot);
        assertEquals(testReservation.getTimeslot(), otherTimeSlot);
    }

    @Test
    void constructorTest() {
        Reservation emptyReservation = new Reservation();
        assertNull(emptyReservation.getUser());

        Reservation nearlyEmptyReservation = new Reservation(
                user,
                otherRoom,
                timeSlot
        );

        assertNull(nearlyEmptyReservation.getReservationID());
    }

    @Test
    void equalityTest() {
        assertEquals(testReservation, identicalReservation);
        assertEquals(testReservation, testReservation);

        assertNotEquals(testReservation, otherReservation);
        assertNotEquals(testReservation, null);
    }
}
