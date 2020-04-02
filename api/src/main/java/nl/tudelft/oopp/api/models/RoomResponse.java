package nl.tudelft.oopp.api.models;

import java.util.List;

public class RoomResponse {

    private List<Room> roomList;

    public RoomResponse() {
    }

    public RoomResponse(List<Room> roomList) {
        this.roomList = roomList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }
}
