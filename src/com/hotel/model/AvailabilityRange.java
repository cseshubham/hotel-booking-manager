package com.hotel.model;

import java.time.LocalDate;

public class AvailabilityRange {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int availableRooms;

    public AvailabilityRange(LocalDate startDate, LocalDate endDate, int availableRooms) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.availableRooms = availableRooms;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }
}
