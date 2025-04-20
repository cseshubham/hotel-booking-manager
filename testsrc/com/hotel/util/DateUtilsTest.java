package com.hotel.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DateUtilsTest {

    @Test
    public void testIsDateInRange_exactMatch() {
        LocalDate target = LocalDate.of(2024, 4, 20);
        LocalDate start = LocalDate.of(2024, 4, 20);
        LocalDate end = LocalDate.of(2024, 4, 25);

        assertTrue(DateUtils.isDateInRange(target, start, end));
    }

    @Test
    public void testIsDateInRange_insideRange() {
        LocalDate target = LocalDate.of(2024, 4, 22);
        LocalDate start = LocalDate.of(2024, 4, 20);
        LocalDate end = LocalDate.of(2024, 4, 25);

        assertTrue(DateUtils.isDateInRange(target, start, end));
    }

    @Test
    public void testIsDateInRange_outsideRange_before() {
        LocalDate target = LocalDate.of(2024, 4, 19);
        LocalDate start = LocalDate.of(2024, 4, 20);
        LocalDate end = LocalDate.of(2024, 4, 25);

        assertFalse(DateUtils.isDateInRange(target, start, end));
    }

    @Test
    public void testIsDateInRange_outsideRange_after() {
        LocalDate target = LocalDate.of(2024, 4, 26);
        LocalDate start = LocalDate.of(2024, 4, 20);
        LocalDate end = LocalDate.of(2024, 4, 25);

        assertFalse(DateUtils.isDateInRange(target, start, end));
    }

    @Test
    public void testGetDatesInRange() {
        LocalDate start = LocalDate.of(2024, 4, 20);
        LocalDate end = LocalDate.of(2024, 4, 23);

        List<LocalDate> result = DateUtils.getDatesInRange(start, end);

        assertEquals(3, result.size());
        assertEquals(LocalDate.of(2024, 4, 20), result.get(0));
        assertEquals(LocalDate.of(2024, 4, 21), result.get(1));
        assertEquals(LocalDate.of(2024, 4, 22), result.get(2));
    }

    @Test
    public void testGetDatesInRange_emptyRange() {
        LocalDate start = LocalDate.of(2024, 4, 25);
        LocalDate end = LocalDate.of(2024, 4, 25);

        List<LocalDate> result = DateUtils.getDatesInRange(start, end);

        assertTrue(result.isEmpty());
    }
}
