package com.example.cdladv.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @MapKey(name = "sku")
    private Map<String, OrderLine> orderLines = new HashMap<>();

    private BigDecimal finalPrice;

    public enum Status {
        IN_PROGRESS, COMPLETED
    }

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Transient
    private BigDecimal runningTotal;

    public void addItem(Item item) {

        OrderLine orderLine = orderLines.get(item.getSku());

        if (orderLine == null) {
            orderLine = new OrderLine();
            orderLine.setOrderId(id);
            orderLine.setItem(item);
            orderLine.setSku(item.getSku());
            orderLines.put(item.getSku(), orderLine);
        }

        orderLine.increase();
        updateRunningTotal();
    }

    public void removeItem(Item item) {
        OrderLine orderLine = orderLines.get(item.getSku());

        if (orderLine == null) {
            return;
        }

        orderLine.decrease();
    }

    private void updateRunningTotal() {
        runningTotal = orderLines.values().stream()
                .map(OrderLine::getOrderLinePrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
        ;
    }
}
