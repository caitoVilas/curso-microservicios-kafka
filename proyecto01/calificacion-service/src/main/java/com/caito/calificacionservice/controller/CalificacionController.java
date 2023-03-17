package com.caito.calificacionservice.controller;

import com.caito.calificacionservice.entity.Calificacion;
import com.caito.calificacionservice.service.contract.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calificaciones")
public class CalificacionController {
    @Autowired
    private CalificacionService calificacionService;

    @PostMapping
    public ResponseEntity<Calificacion> save(@RequestBody Calificacion calificacion){
        return ResponseEntity.status(HttpStatus.CREATED).body(calificacionService.create(calificacion));
    }

    @GetMapping("/{calificacionId}")
    public ResponseEntity<Calificacion> getCalificacion(@PathVariable String calificacionId){
        return ResponseEntity.ok(calificacionService.getCalificacion(calificacionId));
    }

    @GetMapping
    public ResponseEntity<List<Calificacion>> getAll(){
        return ResponseEntity.ok(calificacionService.getAll());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Calificacion>> getUsuario(@PathVariable Long usuarioId){
        return ResponseEntity.ok(calificacionService.getByUser(usuarioId));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Calificacion>> getHotel(@PathVariable Long hotelId){
        return ResponseEntity.ok(calificacionService.getByHotel(hotelId));
    }
}
