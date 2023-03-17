package com.caito.usarioservice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Hotel {

    private Long hotelId;
    @Column(length = 30)
    private String nombre;
    private String ubicacion;
    private String informacion;
}
