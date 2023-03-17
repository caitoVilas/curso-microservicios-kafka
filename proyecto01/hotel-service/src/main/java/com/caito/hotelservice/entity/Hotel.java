package com.caito.hotelservice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "hoteles")
@Data
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;
    @Column(length = 30)
    private String nombre;
    private String ubicacion;
    private String informacion;
}
