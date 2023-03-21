package com.caito.inventarioservice.controller;

import com.caito.inventarioservice.dto.InventarioResponseDTO;
import com.caito.inventarioservice.service.contract.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioConrtroller {
    @Autowired
    private InventarioService inventarioService;

    @GetMapping()
    public List<InventarioResponseDTO> isStock(@RequestParam List<String> codigoSku){
        return inventarioService.buscarPorScu(codigoSku);
    }
}
