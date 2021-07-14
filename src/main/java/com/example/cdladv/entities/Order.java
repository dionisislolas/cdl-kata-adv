package com.example.cdladv.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@EqualsAndHashCode(of = "id")
public class Order implements Serializable {
    private static final long serialVersionUID = 2575587191157248166L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "orderId")
    private List<OrderLine> orderLineList;

    private BigDecimal finalPrice;

    public enum Status {
        IN_PROGRESS, COMPLETED
    }

    @Enumerated(value = EnumType.STRING)
    private Status status;
}
