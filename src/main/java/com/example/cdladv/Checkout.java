package com.example.cdladv;

import com.example.cdladv.entities.Item;
import com.example.cdladv.entities.Order;
import com.example.cdladv.exceptions.ItemNotFoundException;
import com.example.cdladv.exceptions.OrderNotFoundException;
import com.example.cdladv.repository.ItemRepository;
import com.example.cdladv.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class Checkout {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Order addItem(String sku, long orderId) {
        Item item = itemRepository.findById(sku).orElseThrow(() -> new ItemNotFoundException(sku));
        Order order = orderRepository.findById(orderId).orElseThrow(()->new OrderNotFoundException(orderId));
        order.addItem(item);
        return order;
    }

    public Order removeItem(String sku, long orderId) {
        Item item = itemRepository.findById(sku).orElseThrow(() -> new ItemNotFoundException(sku));
        Order order = orderRepository.findById(orderId).orElseThrow(()->new OrderNotFoundException(orderId));
        order.removeItem(item);
        return order;
    }

    public Order completeOrder(long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new OrderNotFoundException(orderId));
        order.setStatus(Order.Status.COMPLETED);
        order.setFinalPrice(order.getRunningTotal());

        orderRepository.save(order);
        return order;
    }

    public long createNewOrder() {
        Order newOrder = new Order();
        newOrder.setStatus(Order.Status.IN_PROGRESS);
        newOrder.setFinalPrice(BigDecimal.ZERO);
        newOrder = orderRepository.save(newOrder);
        return newOrder.getId();
    }
}
