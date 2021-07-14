package com.example.cdladv.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "items")
@Data
@EqualsAndHashCode(of = "sku")
public class Item implements Serializable {
    private static final long serialVersionUID = -9028594448982817081L;
    @Id
    private String sku;
    private BigDecimal price;
}
