package com.hotel.model;

import com.hotel.constant.RoomTypeCode;

import java.util.List;

public class RoomType {
    private RoomTypeCode code;
    private String description;
    private List<String> amenities;
    private List<String> features;

    public RoomType() {}

    // Getters and setters

    public RoomTypeCode getCode() {
        return code;
    }

    public void setCode(RoomTypeCode code) {
        this.code = code;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<String> getAmenities() { return amenities; }
    public void setAmenities(List<String> amenities) { this.amenities = amenities; }
    public List<String> getFeatures() { return features; }
    public void setFeatures(List<String> features) { this.features = features; }
}