package com.caito.productoservice.service.impl;

import com.caito.productoservice.dto.ProductoDTO;
import com.caito.productoservice.dto.ProductoNuevoDTO;
import com.caito.productoservice.model.Producto;
import com.caito.productoservice.repository.ProductoRepository;
import com.caito.productoservice.service.contract.ProductoService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;


    @Override
    public ProductoDTO create(ProductoNuevoDTO dto) {
        log.info("inicio servicio crear producto");
        log.info("creando producto...");
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        Producto pNuevo = productoRepository.save(producto);
        log.info("Producto{}","ha sido creado");
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(pNuevo.getId());
        productoDTO.setNombre(pNuevo.getNombre());
        productoDTO.setDescripcion(pNuevo.getDescripcion());
        productoDTO.setPrecio(pNuevo.getPrecio());
        return productoDTO;
    }

    @Override
    public List<ProductoDTO> verTodos() {
        log.info("inicio servicio buscar productos...");
        log.info("buscando productos");
        List<Producto> productos = productoRepository.findAll();
        return productos.stream().map(this::mapProductoDTO).collect(Collectors.toList());
    }

    private ProductoDTO mapProductoDTO(Producto producto) {
        ProductoDTO response = new ProductoDTO();
        response.setId(producto.getId());
        response.setNombre(producto.getNombre());
        response.setDescripcion(producto.getDescripcion());
        response.setPrecio(producto.getPrecio());
        return response;
    }


}
