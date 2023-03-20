package com.caito.productoservice;

import com.caito.productoservice.dto.ProductoDTO;
import com.caito.productoservice.dto.ProductoNuevoDTO;
import com.caito.productoservice.service.contract.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoDTO> create(@RequestBody ProductoNuevoDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getAll(){
        List<ProductoDTO> productos = productoService.verTodos();
        if (productos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(productos);
    }
}
