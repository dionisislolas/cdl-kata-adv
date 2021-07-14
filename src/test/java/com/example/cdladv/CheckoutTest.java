package com.example.cdladv;

import com.example.cdladv.entities.Item;
import com.example.cdladv.entities.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CheckoutTest {

    @Autowired
    private Checkout checkout;

    @BeforeEach
    void setUp() {
    }

    @Test
    void addItem() {

        long orderId = checkout.createNewOrder();

        checkout.addItem("A", orderId);
        checkout.addItem("A", orderId);
        checkout.addItem("A", orderId);
        checkout.addItem("A", orderId);
        checkout.addItem("B", orderId);
        checkout.addItem("B", orderId);
        Order order = checkout.addItem("B", orderId);
        checkout.completeOrder(orderId);

        assertEquals(BigDecimal.valueOf(2.55), order.getFinalPrice());
    }

    @Test
    void removeItem() {
        long orderId = checkout.createNewOrder();

        checkout.addItem("A", orderId);
        checkout.addItem("A", orderId);
        checkout.addItem("A", orderId);
        checkout.addItem("A", orderId);
        checkout.addItem("B", orderId);
        checkout.addItem("B", orderId);
        checkout.removeItem("A", orderId);
        checkout.removeItem("B", orderId);
        Order order = checkout.addItem("B", orderId);
        checkout.completeOrder(orderId);

        assertEquals(BigDecimal.valueOf(1.75), order.getFinalPrice());
    }

    @Test
    void completeOrder() {
        long orderId = checkout.createNewOrder();
        Order order = checkout.addItem("A", orderId);
        checkout.completeOrder(orderId);
        assertEquals(Order.Status.COMPLETED, order.getStatus());
    }

    @Test
    void createNewOrder() {
        long orderId = checkout.createNewOrder();
        assertEquals(1, orderId);
        orderId = checkout.createNewOrder();
        assertEquals(2, orderId);
        orderId = checkout.createNewOrder();
        assertEquals(3, orderId);
    }
}