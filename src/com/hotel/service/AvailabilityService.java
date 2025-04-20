package com.hotel.service;

import com.hotel.constant.RoomTypeCode;
import com.hotel.model.AvailabilityRange;
import com.hotel.util.AvailabilityFormatter;
import com.hotel.util.DateUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AvailabilityService {
    private final HotelCache hotelCache;
    private final BookingCache bookingCache;

    public AvailabilityService(HotelCache hotelCache, BookingCache bookingCache) {
        this.hotelCache = hotelCache;
        this.bookingCache = bookingCache;
    }

    public int checkAvailabilityForDate(String hotelId, LocalDate date, RoomTypeCode roomTypeCode) {
        int totalRooms = hotelCache.getTotalRoomsOfType(hotelId, roomTypeCode);
        long bookedRooms = getBookedRooms(hotelId, date, roomTypeCode);
        return totalRooms - (int) bookedRooms;
    }

    public int checkAvailabilityForDate(String hotelId, LocalDate start, LocalDate end, RoomTypeCode roomTypeCode) {
        int totalRooms = hotelCache.getTotalRoomsOfType(hotelId, roomTypeCode);
        List<LocalDate> dates = DateUtils.getDatesInRange(start, end);

        int maxBooked = dates.stream()
                .mapToInt(date -> (int) getBookedRooms(hotelId, date, roomTypeCode))
                .max()
                .orElse(0);

        return totalRooms - maxBooked;
    }

    public String searchAvailability(String hotelId, int daysAhead, RoomTypeCode roomTypeCode) {
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusDays(daysAhead);
        List<LocalDate> dates = DateUtils.getDatesInRange(start, end);

        List<AvailabilityRange> ranges = new ArrayList<>();
        LocalDate rangeStart = null;
        Integer currentCount = null;

        for (int i = 0; i < dates.size(); i++) {
            LocalDate date = dates.get(i);
            int count = checkAvailabilityForDate(hotelId, date, roomTypeCode);

            if (currentCount == null || count != currentCount) {
                if (currentCount != null && rangeStart != null && currentCount > 0) {
                    ranges.add(new AvailabilityRange(rangeStart, dates.get(i - 1), currentCount));
                }
                currentCount = count;
                rangeStart = date;
            }

            if (i == dates.size() - 1 && rangeStart != null && currentCount > 0) {
                ranges.add(new AvailabilityRange(rangeStart, date, currentCount));
            }
        }

        return AvailabilityFormatter.format(ranges);
    }


    private long getBookedRooms(String hotelId, LocalDate date, RoomTypeCode roomTypeCode) {
        return bookingCache.getBookings(hotelId, roomTypeCode).stream()
                .filter(b -> DateUtils.isDateInRange(date, b.getArrival(), b.getDeparture()))
                .count();
    }
}
