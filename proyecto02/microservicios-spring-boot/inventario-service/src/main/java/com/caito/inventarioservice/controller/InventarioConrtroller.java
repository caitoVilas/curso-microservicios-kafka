package com.caito.inventarioservice.controller;

import com.caito.inventarioservice.service.contract.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventario")
public class InventarioConrtroller {
    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/{codigoSku}")
    public Boolean isStock(@PathVariable String codigoSku){
        return inventarioService.buscarPorScu(codigoSku);
    }
}
