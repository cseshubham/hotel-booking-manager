package com.hotel.util;

import com.hotel.model.AvailabilityRange;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class AvailabilityFormatter {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static String format(List<AvailabilityRange> ranges) {
        return ranges.stream()
                .map(r -> "(" + r.getStartDate().format(DATE_FORMAT) + "-" + r.getEndDate().format(DATE_FORMAT) + ", " + r.getAvailableRooms() + ")")
                .collect(Collectors.joining(", "));
    }
}
