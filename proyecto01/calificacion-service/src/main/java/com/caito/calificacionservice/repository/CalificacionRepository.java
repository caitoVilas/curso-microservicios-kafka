package com.caito.calificacionservice.repository;

import com.caito.calificacionservice.entity.Calificacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionRepository extends MongoRepository<Calificacion, String> {
    List<Calificacion> findByUsuarioId(Long usuarioId);
    List<Calificacion> findByHotelId(Long hotelId);
}
