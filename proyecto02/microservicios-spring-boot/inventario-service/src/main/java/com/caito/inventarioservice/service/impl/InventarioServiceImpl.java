package com.caito.inventarioservice.service.impl;

import com.caito.inventarioservice.repository.InventarioRepository;
import com.caito.inventarioservice.service.contract.InventarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class InventarioServiceImpl implements InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;


    @Override
    @Transactional(readOnly = true)
    public boolean buscarPorScu(String codigoSku) {
        log.info("inicio servicio buscar por codigoSku");
        log.info("buscando...");
        return inventarioRepository.findByCodigoSku(codigoSku).isPresent();
    }
}
