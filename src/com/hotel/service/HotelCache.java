package com.hotel.service;

import com.hotel.constant.RoomTypeCode;
import com.hotel.model.Hotel;
import com.hotel.model.Room;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HotelCache {
    private Map<String, Map<RoomTypeCode, List<Room>>> hotelRoomMap;

    public void cacheLoad(List<Hotel> hotels) {
        hotelRoomMap = hotels.stream()
                .collect(Collectors.toMap(
                        Hotel::getId,
                        hotel -> hotel.getRooms().stream()
                                .collect(Collectors.groupingBy(Room::getRoomType))
                ));
    }

    public int getTotalRoomsOfType(String hotelId, RoomTypeCode roomType) {
        return hotelRoomMap.getOrDefault(hotelId,Map.of()).getOrDefault(roomType,List.of()).size();
    }
}
