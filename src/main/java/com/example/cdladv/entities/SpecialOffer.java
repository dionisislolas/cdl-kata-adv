package com.example.cdladv.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "special_offers")
@Data
@EqualsAndHashCode(of = "sku")
@AllArgsConstructor
@NoArgsConstructor
public class SpecialOffer implements Serializable {
    private static final long serialVersionUID = 6699861293290280016L;

    @Id
    private String sku;

    private int quantity;

    private BigDecimal specialPrice;
}
