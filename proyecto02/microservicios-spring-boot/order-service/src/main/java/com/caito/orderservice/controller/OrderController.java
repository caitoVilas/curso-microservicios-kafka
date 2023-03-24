package com.caito.orderservice.controller;

import com.caito.orderservice.dto.OrderRequestDTO;
import com.caito.orderservice.repository.OrderRepository;
import com.caito.orderservice.service.contract.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    @CircuitBreaker(name="inventario", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventario")
    @Retry(name = "inventario")
    public CompletableFuture<String> crearOrden(@RequestBody OrderRequestDTO dto){
        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(dto));
    }

    public CompletableFuture<String> fallbackMethod(OrderRequestDTO dto, RuntimeException e){
        return CompletableFuture.supplyAsync(()-> "Opps! ha ocurrido en error");
    }
}
