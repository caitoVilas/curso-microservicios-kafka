package com.caito.calificacionservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("calificacion")
@Data
public class Calificacion {

    @Id
    private String calificacionId;
    private Long usuarioId;
    private Long hotelId;
    private Integer calificacion;
    private String observaciones;
}
