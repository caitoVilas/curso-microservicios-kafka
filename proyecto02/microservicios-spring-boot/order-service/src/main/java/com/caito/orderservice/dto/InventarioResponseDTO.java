package com.caito.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventarioResponseDTO {
    private String codigoSku;
    private boolean isInStock;
}
