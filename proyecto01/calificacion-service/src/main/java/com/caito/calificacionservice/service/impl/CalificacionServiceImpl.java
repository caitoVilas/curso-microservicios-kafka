package com.caito.calificacionservice.service.impl;

import com.caito.calificacionservice.entity.Calificacion;
import com.caito.calificacionservice.exceptions.NotFoundException;
import com.caito.calificacionservice.repository.CalificacionRepository;
import com.caito.calificacionservice.service.contract.CalificacionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CalificacionServiceImpl implements CalificacionService {
    @Autowired
    private CalificacionRepository calificacionRepository;


    @Override
    public Calificacion create(Calificacion calificacion) {
        log.info("inicio servicio alta de calificacion");
        log.info("guardando calificacion...");
        return calificacionRepository.save(calificacion);
    }

    @Override
    public Calificacion getCalificacion(String calificacionId) {
        log.info("inicio servicio buscar calificacion por id");
        log.info("buscando calificacion...");
        return calificacionRepository.findById(calificacionId).orElseThrow(()->{
            log.error("calificacion no encontrada");
            throw new NotFoundException("calificacion no encontrada");
        });
    }

    @Override
    public List<Calificacion> getAll() {
        log.info("inicio servicio buscar todas las calificaciones");
        log.info("buscando calificaciones...");
        return calificacionRepository.findAll();
    }

    @Override
    public List<Calificacion> getByUser(Long userId) {
        log.info("inicio servicio buscar todas las calificaciones por usuario");
        log.info("buscando calificaciones...");
        return calificacionRepository.findByUsuarioId(userId);
    }

    @Override
    public List<Calificacion> getByHotel(Long hotelId) {
        log.info("inicio servicio buscar todas las calificaciones por hotel");
        log.info("buscando calificaciones...");
        return calificacionRepository.findByHotelId(hotelId);
    }
}
