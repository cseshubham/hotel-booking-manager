package com.hotel;

import com.hotel.constant.RoomTypeCode;
import com.hotel.model.Booking;
import com.hotel.model.Hotel;
import com.hotel.service.AvailabilityService;
import com.hotel.service.HotelCache;
import com.hotel.service.BookingCache;
import com.hotel.util.JsonParser;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class HotelBookingApp {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static void main(String[] args) {
        if (args.length < 4 || !args[0].equals("--hotels") || !args[2].equals("--bookings")) {
            System.out.println("Usage: java -cp .;lib/* com.hotel.Main --hotels <hotels_file> --bookings <bookings_file>");
            return;
        }

        try {
            List<Hotel> hotels = JsonParser.parseHotels(args[1]);
            List<Booking> bookings = JsonParser.parseBookings(args[3]);

            HotelCache hotelCache = new HotelCache();
            hotelCache.cacheLoad(hotels);
            BookingCache bookingCache = new BookingCache();
            bookingCache.cacheLoad(bookings);
            AvailabilityService availabilityService = new AvailabilityService(hotelCache, bookingCache);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Hotel Management System started. Enter commands or blank line to exit.");

            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) break;

                try {
                    if (input.startsWith("Availability(") && input.endsWith(")")) {
                        processAvailabilityCommand(input, availabilityService);
                    } else if (input.startsWith("Search(") && input.endsWith(")")) {
                        processSearchCommand(input, availabilityService);
                    } else {
                        System.out.println("Invalid command format");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading files: " + e.getMessage());
        }
    }

    private static void processAvailabilityCommand(String input, AvailabilityService service) {
        String content = input.substring("Availability(".length(), input.length() - 1);
        String[] parts = content.split(",\\s*");

        if (parts.length != 3) {
            System.out.println("Usage: Availability(hotelId, date/range, roomType)");
            return;
        }

        String hotelId = parts[0].trim();
        String dateOrRange = parts[1].trim();
        RoomTypeCode roomTypeCode = RoomTypeCode.valueOf(parts[2].trim());

        if (dateOrRange.contains("-")) {
            String[] dates = dateOrRange.split("-");
            System.out.println(service.checkAvailabilityForDate(hotelId, LocalDate.parse(dates[0], formatter), LocalDate.parse(dates[1], formatter), roomTypeCode));
        } else {
            System.out.println(service.checkAvailabilityForDate(hotelId, LocalDate.parse(dateOrRange, formatter), roomTypeCode));
        }
    }

    private static void processSearchCommand(String input, AvailabilityService service) {
        String content = input.substring("Search(".length(), input.length() - 1);
        String[] parts = content.split(",\\s*");

        if (parts.length != 3) {
            System.out.println("Usage: Search(hotelId, daysAhead, roomType)");
            return;
        }

        String hotelId = parts[0].trim();
        int daysAhead = Integer.parseInt(parts[1].trim());
        RoomTypeCode roomTypeCode = RoomTypeCode.valueOf(parts[2].trim());

        System.out.println(service.searchAvailability(hotelId, daysAhead, roomTypeCode));
    }
}