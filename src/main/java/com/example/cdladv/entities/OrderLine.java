package com.example.cdladv.entities;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "order_lines")
@IdClass(value = OrderLine.ID.class)
@Data
@EqualsAndHashCode(of = {"sku", "orderId"})
public class OrderLine implements Serializable {
    private static final long serialVersionUID = 2024825792289435002L;

    @Id
    private String sku;

    @Id
    private long orderId;

    private int quantityOrdered;

    private BigDecimal orderLinePrice;

    @ManyToOne
    @JoinColumn(name = "sku", insertable = false, updatable = false)
    private Item item;

    public void increase() {
        quantityOrdered++;
        orderLinePrice = calculatePrice();
    }

    private BigDecimal calculatePrice() {
        SpecialOffer specialOffer = item.getSpecialOffer();
        if (quantityOrdered >= specialOffer.getQuantity()) {
            int quotient = quantityOrdered / specialOffer.getQuantity();
            int remainder = quantityOrdered % specialOffer.getQuantity();
            orderLinePrice = orderLinePrice.add(
                    BigDecimal.valueOf(specialOffer.getSpecialPrice().floatValue() * quotient)
            );
            for (int i=0; i<remainder; i++) {
                orderLinePrice = orderLinePrice.add(item.getPrice());
            }
        } else {
            for (int i=0; i<quantityOrdered; i++) {
                orderLinePrice = orderLinePrice.add(item.getPrice());
            }
        }
        return orderLinePrice;
    }

    @Data
    public static class ID implements Serializable {
        private static final long serialVersionUID = -4669092645154418011L;
        private String sku;
        private long orderId;
    }
}
