package com.caito.orderservice.controller;

import com.caito.orderservice.dto.OrderRequestDTO;
import com.caito.orderservice.repository.OrderRepository;
import com.caito.orderservice.service.contract.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> crearOrden(@RequestBody OrderRequestDTO dto){
        orderService.placeOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
