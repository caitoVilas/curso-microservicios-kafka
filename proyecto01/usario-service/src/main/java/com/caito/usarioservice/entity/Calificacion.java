package com.caito.usarioservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

@Data
public class Calificacion {

    private String calificacionId;
    private Long usuarioId;
    private Long hotelId;
    private Integer calificacion;
    private String observaciones;
    private  Hotel hotel;
}
