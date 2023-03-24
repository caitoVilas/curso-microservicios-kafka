package com.caito.orderservice.service.contract;

import com.caito.orderservice.dto.OrderRequestDTO;

public interface OrderService {
    String placeOrder(OrderRequestDTO dto);
}
