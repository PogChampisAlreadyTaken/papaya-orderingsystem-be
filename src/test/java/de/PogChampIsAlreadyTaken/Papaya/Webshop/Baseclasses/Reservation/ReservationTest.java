package de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Reservation;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class ReservationTest {

    @Test
    void testEqualsWithIdenticalReservations() {
        Reservation firstReservation = new Reservation();

        firstReservation.reservation_timestamp = new Timestamp(1642165712);
        firstReservation.tableid = 1;
        firstReservation.phonenumber = Long.parseLong("012334556");
        firstReservation.name = "Peter";

        Reservation secondReservation = new Reservation();

        secondReservation.reservation_timestamp = new Timestamp(1642165712);
        secondReservation.tableid = 1;
        secondReservation.phonenumber = Long.parseLong("012334556");
        secondReservation.name = "Peter";

        boolean response = firstReservation.equals(secondReservation);

        assertTrue(response);
    }

    @Test
    void testEqualsWithDifferentReservations() {
        Reservation firstReservation = new Reservation();

        firstReservation.reservation_timestamp = new Timestamp(1642165712);
        firstReservation.tableid = 1;
        firstReservation.phonenumber = Long.parseLong("012334556");
        firstReservation.name = "Peter";

        Reservation secondReservation = new Reservation();

        secondReservation.reservation_timestamp = new Timestamp(1642165712);
        secondReservation.tableid = 1;
        secondReservation.phonenumber = Long.parseLong("0123345561");
        secondReservation.name = "Peter";

        boolean response =firstReservation.equals(secondReservation);

        assertFalse(response);
    }

    @Test
    void testGetSpecificTablesDuration(){
        Reservation firstReservation = new Reservation();

        //First reservation at 19:51 - beginTime
        firstReservation.reservation_timestamp = new Timestamp(1642186200000L);
        firstReservation.tableid = 1;
        firstReservation.phonenumber = Long.parseLong("012334556");
        firstReservation.name = "Peter";

        Reservation secondReservation = new Reservation();

        //Second reservation at 19:30
        secondReservation.reservation_timestamp = new Timestamp(1642185000000L);
        secondReservation.tableid = 1;
        secondReservation.phonenumber = Long.parseLong("0123345561");
        secondReservation.name = "Peter";

        Reservation thirdReservation = new Reservation();

        //Third reservation at 21:50 - finalTime
        thirdReservation.reservation_timestamp = new Timestamp(1642193400000L);
        thirdReservation.tableid = 1;
        thirdReservation.phonenumber = Long.parseLong("0123345561");
        thirdReservation.name = "Peter";

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(firstReservation);
        reservations.add(secondReservation);
        reservations.add(thirdReservation);

        reservations = Reservation.getSpecificTablesinDuration(reservations, 1642189800000L );


        //assert that second reservation is not in the list because it is not in the duration of +/- 1 hour
        assertFalse(reservations.contains(secondReservation));
        assertTrue(reservations.contains(firstReservation));
        assertTrue(reservations.contains(thirdReservation));
    }
}