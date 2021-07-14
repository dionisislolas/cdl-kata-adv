package com.example.cdladv.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "items")
@Data
@EqualsAndHashCode(of = "sku")
@NoArgsConstructor
public class Item implements Serializable {
    private static final long serialVersionUID = -9028594448982817081L;

    @Id
    private String sku;

    private BigDecimal price;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sku")
    private SpecialOffer specialOffer;

    public Item(String sku, BigDecimal price) {
        this.sku = sku;
        this.price = price;
    }
}
