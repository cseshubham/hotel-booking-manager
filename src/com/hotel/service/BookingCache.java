package com.hotel.service;

import com.hotel.constant.RoomTypeCode;
import com.hotel.model.Booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingCache {
    private final Map<String, Map<RoomTypeCode, List<Booking>>> cache = new HashMap<>();

    public void cacheLoad(List<Booking> bookings) {
        for (Booking booking : bookings) {
            cache
                .computeIfAbsent(booking.getHotelId(), k -> new HashMap<>())
                .computeIfAbsent(booking.getRoomType(), k -> new ArrayList<>())
                .add(booking);
        }
    }

    public List<Booking> getBookings(String hotelId, RoomTypeCode roomTypeCode) {
        return cache.getOrDefault(hotelId, Map.of()).getOrDefault(roomTypeCode, List.of());
    }
}
