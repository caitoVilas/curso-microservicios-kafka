package com.caito.orderservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_line_items")
@Data
public class OrderLinesItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigoSku;
    private BigDecimal precio;
    private Integer cantidad;
}
