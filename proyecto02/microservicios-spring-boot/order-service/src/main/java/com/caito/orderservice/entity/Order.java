package com.caito.orderservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroPedido;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLinesItems> orderLinesItems;
}