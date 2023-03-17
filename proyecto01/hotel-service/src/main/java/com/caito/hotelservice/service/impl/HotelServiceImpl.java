package com.caito.hotelservice.service.impl;

import com.caito.hotelservice.entity.Hotel;
import com.caito.hotelservice.exeption.NotFoundException;
import com.caito.hotelservice.repository.HotelRepository;
import com.caito.hotelservice.service.contract.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;


    @Override
    public Hotel createHotel(Hotel hotel) {
        log.info("iniciando servicio guardar hotel");
        log.info("guardando hotel...");
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotel(Long id) {
        log.info("inicio buscar hotel por id");
        log.info("buscando hotel...");
        return hotelRepository.findById(id).orElseThrow(()->{
            log.error("hotel no encontrado");
            throw new NotFoundException("hotel no encontrado");
        });
    }

    @Override
    public List<Hotel> getAll() {
        log.info("inicio servicio buscar todos los hoteles");
        log.info("buscar hoteles...");
        return hotelRepository.findAll();
    }
}
