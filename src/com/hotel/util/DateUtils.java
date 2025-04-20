package com.hotel.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateUtils {

    public static boolean isDateInRange(LocalDate target, LocalDate start, LocalDate end) {
        return (target.isEqual(start) || target.isAfter(start)) &&
                (target.isEqual(end) || target.isBefore(end));
    }
    public static List<LocalDate> getDatesInRange(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = new ArrayList<>();
        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            dates.add(date);
        }
        return dates;
    }
}