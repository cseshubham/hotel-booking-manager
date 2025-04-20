package com.hotel.util;

import com.hotel.model.Booking;
import com.hotel.model.Hotel;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {

    private final String hotelJsonPath = "testresources/hotels.json";
    private final String bookingJsonPath = "testresources/bookings.json";

    @Test
    public void testParseHotels() throws IOException {
        List<Hotel> hotels = JsonParser.parseHotels(hotelJsonPath);

        assertNotNull(hotels);
        assertFalse(hotels.isEmpty(), "Hotels list should not be empty");

        Hotel hotel = hotels.get(0);
        assertNotNull(hotel.getId());
        assertNotNull(hotel.getRooms());
    }

    @Test
    public void testParseBookings() throws IOException {
        List<Booking> bookings = JsonParser.parseBookings(bookingJsonPath);

        assertNotNull(bookings);
        assertFalse(bookings.isEmpty(), "Bookings list should not be empty");

        Booking booking = bookings.get(0);
        assertNotNull(booking.getHotelId());
        assertNotNull(booking.getRoomType());
        assertNotNull(booking.getArrival());
        assertNotNull(booking.getDeparture());
    }
}
