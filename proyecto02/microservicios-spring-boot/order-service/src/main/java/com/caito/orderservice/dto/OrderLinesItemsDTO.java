package com.caito.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderLinesItemsDTO {
    private Long id;
    private String codigoSku;
    private BigDecimal precio;
    private Integer cantidad;
}
