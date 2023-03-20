package com.caito.productoservice.service.contract;

import com.caito.productoservice.dto.ProductoDTO;
import com.caito.productoservice.dto.ProductoNuevoDTO;

import java.util.List;

public interface ProductoService {
    ProductoDTO create(ProductoNuevoDTO dto);
    List<ProductoDTO> verTodos();
}
