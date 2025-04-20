package com.hotel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotel.constant.RoomTypeCode;

import java.time.LocalDate;

public class Booking {
    private String hotelId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private LocalDate arrival;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private LocalDate departure;
    private RoomTypeCode roomType;
    private String roomRate;

    public Booking() {}

    // Getters and setters
    public String getHotelId() { return hotelId; }
    public void setHotelId(String hotelId) { this.hotelId = hotelId; }
    public LocalDate getArrival() { return arrival; }
    public void setArrival(LocalDate arrival) { this.arrival = arrival; }
    public LocalDate getDeparture() { return departure; }
    public void setDeparture(LocalDate departure) { this.departure = departure; }
    public RoomTypeCode getRoomType() { return roomType; }
    public void setRoomType(RoomTypeCode roomType) { this.roomType = roomType; }
    public String getRoomRate() { return roomRate; }
    public void setRoomRate(String roomRate) { this.roomRate = roomRate; }
}