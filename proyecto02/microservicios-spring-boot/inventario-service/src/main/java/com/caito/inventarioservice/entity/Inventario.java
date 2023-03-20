package com.caito.inventarioservice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "inventarios")
@Data
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigoSku;
    private Integer cantidad;
}
