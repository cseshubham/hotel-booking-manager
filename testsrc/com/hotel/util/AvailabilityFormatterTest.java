package com.hotel.util;

import com.hotel.model.AvailabilityRange;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AvailabilityFormatterTest {

    @Test
    public void testFormatSingleRange() {
        List<AvailabilityRange> ranges = List.of(
                new AvailabilityRange(LocalDate.of(2025, 4, 20), LocalDate.of(2025, 4, 25), 3)
        );

        String result = AvailabilityFormatter.format(ranges);
        assertEquals("(20250420-20250425, 3)", result);
    }

    @Test
    public void testFormatMultipleRanges() {
        List<AvailabilityRange> ranges = Arrays.asList(
                new AvailabilityRange(LocalDate.of(2025, 4, 20), LocalDate.of(2025, 4, 20), 2),
                new AvailabilityRange(LocalDate.of(2025, 4, 21), LocalDate.of(2025, 4, 22), 1),
                new AvailabilityRange(LocalDate.of(2025, 4, 23), LocalDate.of(2025, 4, 23), 0)
        );

        String expected = "(20250420-20250420, 2), (20250421-20250422, 1), (20250423-20250423, 0)";
        String result = AvailabilityFormatter.format(ranges);
        assertEquals(expected, result);
    }

    @Test
    public void testFormatEmptyRangeList() {
        List<AvailabilityRange> ranges = List.of();
        String result = AvailabilityFormatter.format(ranges);
        assertEquals("", result);
    }
}
