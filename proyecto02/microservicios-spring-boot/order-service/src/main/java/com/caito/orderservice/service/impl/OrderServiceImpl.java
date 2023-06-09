package com.caito.orderservice.service.impl;

import brave.Span;
import brave.Tracer;
import com.caito.orderservice.dto.InventarioResponseDTO;
import com.caito.orderservice.dto.OrderLinesItemsDTO;
import com.caito.orderservice.dto.OrderRequestDTO;
import com.caito.orderservice.entity.Order;
import com.caito.orderservice.entity.OrderLinesItems;
import com.caito.orderservice.event.OrderPlacedEvent;
import com.caito.orderservice.repository.OrderRepository;
import com.caito.orderservice.service.contract.OrderService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private WebClient.Builder webClient;
    @Autowired
    private Tracer tracer;
    @Autowired
    private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;


    @Override
    @Transactional()
    @SneakyThrows
    public String placeOrder(OrderRequestDTO dto) {
        log.info("iniciando servicio crear pedidos");
        //Thread.sleep(10000);
        Order order = new Order();
        log.info("end wait");
        order.setNumeroPedido(UUID.randomUUID().toString());
        List<OrderLinesItems> orderLineItems = dto.getOrderListItems()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        order.setOrderLinesItems(orderLineItems);
        List<String> codigoSku = order.getOrderLinesItems().stream()
                        .map(OrderLinesItems::getCodigoSku)
                                .collect(Collectors.toList());
        Span inventarioServiceLookup = tracer.nextSpan().name("InventarioServiceLookup");
        try(Tracer.SpanInScope isLookUp = tracer.withSpanInScope(inventarioServiceLookup.start())) {
            inventarioServiceLookup.tag("call", "inventario-service");
            InventarioResponseDTO[] inventarioArray = webClient.build().get()
                    .uri("http://INVENTARIO-SERVICE/api/inventario", uriBuilder ->
                            uriBuilder.queryParam("codigoSku", codigoSku).build())
                    .retrieve()
                    .bodyToMono(InventarioResponseDTO[].class)
                    .block();
            log.info("{} ", codigoSku);
            boolean allProductsInStock = Arrays.stream(inventarioArray)
                    .allMatch(InventarioResponseDTO::isInStock);
            if (allProductsInStock){
                orderRepository.save(order);
                kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(
                        order.getNumeroPedido()));
                return "pedido realizado con exito";
            }else {
                throw new  IllegalArgumentException("El producto no esta en stock");
            }
        }finally {
            inventarioServiceLookup.flush();
        }

    }

    private OrderLinesItems mapToDto(OrderLinesItemsDTO orderLinesItemsDTO) {
        OrderLinesItems orderLinesItems = new OrderLinesItems();
        orderLinesItems.setPrecio(orderLinesItemsDTO.getPrecio());
        orderLinesItems.setCantidad(orderLinesItemsDTO.getCantidad());
        orderLinesItems.setCodigoSku(orderLinesItemsDTO.getCodigoSku());
        return orderLinesItems;
    }


}
