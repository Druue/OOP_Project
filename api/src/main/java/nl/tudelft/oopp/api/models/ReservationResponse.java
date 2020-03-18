package nl.tudelft.oopp.api.models;

import java.util.List;

public class ReservationResponse {

    List<Reservation> reservationList;

    public ReservationResponse(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}

