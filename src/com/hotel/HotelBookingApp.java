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

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static void main(String[] args) {
        if (!isValidArgs(args)) {
            System.out.println("Usage: java -cp .;lib/* com.hotel.Main --hotels <hotels_file> --bookings <bookings_file>");
            return;
        }
        try {
            List<Hotel> hotels = JsonParser.parseHotels(args[1]);
            List<Booking> bookings = JsonParser.parseBookings(args[3]);
            AvailabilityService availabilityService = initializeService(hotels, bookings);
            runCommandLoop(availabilityService);
        } catch (IOException e) {
            System.out.println("Error loading files: " + e.getMessage());
        }
    }

    private static boolean isValidArgs(String[] args) {
        return args.length >= 4 && "--hotels".equals(args[0]) && "--bookings".equals(args[2]);
    }

    private static AvailabilityService initializeService(List<Hotel> hotels, List<Booking> bookings) {
        HotelCache hotelCache = new HotelCache();
        hotelCache.cacheLoad(hotels);

        BookingCache bookingCache = new BookingCache();
        bookingCache.cacheLoad(bookings);

        return new AvailabilityService(hotelCache, bookingCache);
    }

    private static void runCommandLoop(AvailabilityService service) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hotel Management System started. Enter commands or blank line to exit.");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) break;
            try {
                if (input.startsWith("Availability(") && input.endsWith(")")) {
                    processAvailabilityCommand(input, service);
                } else if (input.startsWith("Search(") && input.endsWith(")")) {
                    processSearchCommand(input, service);
                } else {
                    System.out.println("Invalid command format.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void processAvailabilityCommand(String input, AvailabilityService service) {
        String content = extractContent(input, "Availability");
        String[] parts = content.split(",\\s*");
        if (parts.length != 3) {
            System.out.println("Usage: Availability(hotelId, date/range, roomType)");
            return;
        }
        String hotelId = parts[0];
        String dateOrRange = parts[1];
        RoomTypeCode roomTypeCode = RoomTypeCode.valueOf(parts[2]);
        if (dateOrRange.contains("-")) {
            String[] dates = dateOrRange.split("-");
            LocalDate start = LocalDate.parse(dates[0], FORMATTER);
            LocalDate end = LocalDate.parse(dates[1], FORMATTER);
            System.out.println(service.checkAvailabilityForDate(hotelId, start, end, roomTypeCode));
        } else {
            LocalDate date = LocalDate.parse(dateOrRange, FORMATTER);
            System.out.println(service.checkAvailabilityForDate(hotelId, date, roomTypeCode));
        }
    }

    private static void processSearchCommand(String input, AvailabilityService service) {
        String content = extractContent(input, "Search");
        String[] parts = content.split(",\\s*");
        if (parts.length != 3) {
            System.out.println("Usage: Search(hotelId, daysAhead, roomType)");
            return;
        }
        String hotelId = parts[0];
        int daysAhead = Integer.parseInt(parts[1]);
        RoomTypeCode roomTypeCode = RoomTypeCode.valueOf(parts[2]);
        System.out.println(service.searchAvailability(hotelId, daysAhead, roomTypeCode));
    }

    private static String extractContent(String input, String command) {
        return input.substring(command.length() + 1, input.length() - 1);
    }
}
