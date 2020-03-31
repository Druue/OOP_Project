package nl.tudelft.oopp.api;

import java.sql.Timestamp;
import nl.tudelft.oopp.api.models.TimeSlot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TimeSlotTest {

    TimeSlot testTimeSlot;
    TimeSlot identicalTimeSlot;
    TimeSlot otherTimeSlot;

    @BeforeEach
    void beforeEach() {

        testTimeSlot = new TimeSlot(
                42L,
                new Timestamp(1L),
                new Timestamp(2L)
        );

        identicalTimeSlot = new TimeSlot(
                42L,
                new Timestamp(1L),
                new Timestamp(2L)
        );

        otherTimeSlot = new TimeSlot(
                new Timestamp(100L),
                new Timestamp(450L)
        );

    }

    @Test
    void testEquality() {
        Assertions.assertEquals(testTimeSlot, identicalTimeSlot);
        Assertions.assertEquals(testTimeSlot, testTimeSlot);

        Assertions.assertNotEquals(testTimeSlot, null);
        Assertions.assertNotEquals(testTimeSlot, otherTimeSlot);
    }

    @Test
    void testGetterSetters() {

        testTimeSlot.setId(42L);
        Assertions.assertEquals(testTimeSlot.getId(), 42L);

        testTimeSlot.setStartTime(new Timestamp(50L));
        Assertions.assertEquals(testTimeSlot.getStartTime(), new Timestamp(50L));

        testTimeSlot.setEndTime(new Timestamp(70L));
        Assertions.assertEquals(testTimeSlot.getEndTime(), new Timestamp(70L));
    }

    @Test
    void testConstructors() {
        TimeSlot emptyTimeSlot = new TimeSlot();

        Assertions.assertNull(emptyTimeSlot.getId());
        Assertions.assertNull(emptyTimeSlot.getEndTime());
        Assertions.assertNull(emptyTimeSlot.getStartTime());


        // In beforeEach(), otherTimeSlot is instantiated without an Id.
        Assertions.assertNull(otherTimeSlot.getId());
    }
}
