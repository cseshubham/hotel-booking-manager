package com.hotel.service;

import com.hotel.constant.RoomTypeCode;
import com.hotel.util.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AvailabilityServiceTest {

    private HotelCache hotelCache;
    private BookingCache bookingCache;
    private AvailabilityService availabilityService;

    @BeforeEach
    public void setup() throws IOException {
        hotelCache = new HotelCache();
        hotelCache.cacheLoad(JsonParser.parseHotels("testresources/hotels.json"));

        bookingCache = new BookingCache();
        bookingCache.cacheLoad(JsonParser.parseBookings("testresources/bookings.json"));

        availabilityService = new AvailabilityService(hotelCache, bookingCache);
    }

    @Test
    public void testCheckAvailabilityForSpecificDate() {
        LocalDate date = LocalDate.of(2025, 4, 20);
        int available = availabilityService.checkAvailabilityForDate("H1", date, RoomTypeCode.SGL);
        assertTrue(available >= 0, "Available rooms should not be negative");
    }

    @Test
    public void testCheckAvailabilityForDateRange() {
        LocalDate start = LocalDate.of(2025, 4, 20);
        LocalDate end = LocalDate.of(2025, 4, 22);
        int available = availabilityService.checkAvailabilityForDate("H1", start, end, RoomTypeCode.SGL);
        assertTrue(available < 0, "Available rooms should not be negative");
    }

    @Test
    public void testSearchAvailability() {
        String result = availabilityService.searchAvailability("H1", 10, RoomTypeCode.SGL);
        assertNotNull(result);
        assertFalse(result.isEmpty(), "Availability result should not be empty");
        System.out.println("Search result: " + result);
    }
}
