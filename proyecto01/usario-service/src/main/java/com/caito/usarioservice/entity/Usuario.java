package com.caito.usarioservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;
    @Column(length = 20)
    private String nombre;
    @Column(length = 70)
    private String email;
    private String informacion;
    @Transient
    private List<Calificacion> calificaciones = new ArrayList<>();
}
