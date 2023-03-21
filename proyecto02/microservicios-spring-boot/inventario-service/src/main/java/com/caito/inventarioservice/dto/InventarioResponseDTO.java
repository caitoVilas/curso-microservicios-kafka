package com.caito.inventarioservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventarioResponseDTO {
    private String codigoSku;
    private boolean inStock;
}
