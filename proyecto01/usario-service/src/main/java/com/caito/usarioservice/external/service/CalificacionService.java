package com.caito.usarioservice.external.service;

import com.caito.usarioservice.entity.Calificacion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "CALIFICACION-SERVICE")
public interface CalificacionService {
    @PostMapping
    public ResponseEntity<Calificacion> guardarCalificacion(@RequestBody Calificacion calificacion);

    @GetMapping
    public ResponseEntity<List<Calificacion>> getAll();

    @GetMapping("/{calificacionId}")
    public ResponseEntity<Calificacion> getCalificacion(@PathVariable String calificacionId);

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Calificacion>> getByUser(@PathVariable Long usuarioId);

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Calificacion>> getByHotel(@PathVariable Long hotelId);
}
