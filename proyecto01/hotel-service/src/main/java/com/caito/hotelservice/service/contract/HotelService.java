package com.caito.hotelservice.service.contract;

import com.caito.hotelservice.entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel createHotel(Hotel hotel);
    Hotel getHotel(Long id);
    List<Hotel> getAll();
}
