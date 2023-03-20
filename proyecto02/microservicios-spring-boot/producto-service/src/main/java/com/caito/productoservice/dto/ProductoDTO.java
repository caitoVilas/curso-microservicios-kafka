package com.caito.productoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoDTO {
    private String id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
}
