package com.example.cdladv.configuration;

import com.example.cdladv.entities.Item;
import com.example.cdladv.entities.SpecialOffer;
import com.example.cdladv.repository.ItemRepository;
import com.sun.tools.javac.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Configuration
public class DatabaseInitializer {

    @Autowired
    private ItemRepository repository;

    @PostConstruct
    public void init() {

        // item a
        Item a = new Item("A", BigDecimal.valueOf(0.50));
        SpecialOffer aOffer = new SpecialOffer("A", 3, BigDecimal.valueOf(1.30));
        a.setSpecialOffer(aOffer);

        // item b
        Item b = new Item("B", BigDecimal.valueOf(0.30));
        SpecialOffer bOffer = new SpecialOffer("B", 2, BigDecimal.valueOf(0.45));
        b.setSpecialOffer(bOffer);

        // item c
        Item c = new Item("C", BigDecimal.valueOf(0.20));

        // item d
        Item d = new Item("D", BigDecimal.valueOf(0.15));

        repository.saveAll(List.of(a, b, c, d));
    }
}
