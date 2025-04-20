package com.hotel.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hotel.model.Booking;
import com.hotel.model.Hotel;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
    public static List<Hotel> parseHotels(String filePath) throws IOException {
        return objectMapper.readValue(new File(filePath), new TypeReference<List<Hotel>>() {});
    }

    public static List<Booking> parseBookings(String filePath) throws IOException {
        return objectMapper.readValue(new File(filePath), new TypeReference<List<Booking>>() {});
    }
}