package com.hotel.model;

import com.hotel.constant.RoomTypeCode;

public class Room {
    private RoomTypeCode roomType;
    private String roomId;

    public Room() {}

    // Getters and setters

    public void setRoomType(RoomTypeCode roomType) {
        this.roomType = roomType;
    }

    public RoomTypeCode getRoomType() {
        return roomType;
    }

   public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }
}