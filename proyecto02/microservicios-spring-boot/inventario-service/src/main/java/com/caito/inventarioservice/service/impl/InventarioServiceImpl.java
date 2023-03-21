package com.caito.inventarioservice.service.impl;

import com.caito.inventarioservice.dto.InventarioResponseDTO;
import com.caito.inventarioservice.repository.InventarioRepository;
import com.caito.inventarioservice.service.contract.InventarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InventarioServiceImpl implements InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;


    @Override
    @Transactional(readOnly = true)
    public List<InventarioResponseDTO> buscarPorScu(List<String> codigoSku) {
        log.info("inicio servicio buscar por codigoSku");
        log.info("buscando...");
        return inventarioRepository.findByCodigoSkuIn(codigoSku).stream()
                .map(inventario ->{
                    InventarioResponseDTO response = new InventarioResponseDTO();
                    response.setCodigoSku(inventario.getCodigoSku());
                    response.setInStock(inventario.getCantidad() > 0);
                    return response;
                }).collect(Collectors.toList());
    }
}
