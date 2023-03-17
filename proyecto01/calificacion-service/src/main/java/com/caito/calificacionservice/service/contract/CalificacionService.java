package com.caito.calificacionservice.service.contract;

import com.caito.calificacionservice.entity.Calificacion;

import java.util.List;

public interface CalificacionService {
    Calificacion create(Calificacion calificacion);
    Calificacion getCalificacion(String calificacionId);
    List<Calificacion> getAll();
    List<Calificacion> getByUser(Long userId);
    List<Calificacion> getByHotel(Long hotelId);
}
