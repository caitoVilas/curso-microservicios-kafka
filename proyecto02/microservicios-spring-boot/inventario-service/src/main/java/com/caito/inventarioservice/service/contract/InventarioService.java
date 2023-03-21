package com.caito.inventarioservice.service.contract;

import com.caito.inventarioservice.dto.InventarioResponseDTO;

import java.util.List;

public interface InventarioService {
    List<InventarioResponseDTO> buscarPorScu(List<String> codigoSku);
}
