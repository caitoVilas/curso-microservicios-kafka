package com.caito.orderservice.service.impl;

import com.caito.orderservice.dto.OrderLinesItemsDTO;
import com.caito.orderservice.dto.OrderRequestDTO;
import com.caito.orderservice.entity.Order;
import com.caito.orderservice.entity.OrderLinesItems;
import com.caito.orderservice.repository.OrderRepository;
import com.caito.orderservice.service.contract.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public void placeOrder(OrderRequestDTO dto) {
        Order order = new Order();
        order.setNumeroPedido(UUID.randomUUID().toString());
        List<OrderLinesItems> orderLinesItems = dto.getOrderListItems()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        order.setOrderLinesItems(orderLinesItems);
        orderRepository.save(order);
    }

    private OrderLinesItems mapToDto(OrderLinesItemsDTO orderLinesItemsDTO) {
        OrderLinesItems orderLinesItems = new OrderLinesItems();
        orderLinesItems.setPrecio(orderLinesItemsDTO.getPrecio());
        orderLinesItems.setCantidad(orderLinesItemsDTO.getCantidad());
        orderLinesItems.setCodigoSku(orderLinesItemsDTO.getCodigoSku());
        return orderLinesItems;
    }


}
