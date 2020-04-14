package com.mycompany.orderapi.service;

import com.mycompany.orderapi.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrders();

    Order validateAndGetOrder(String id);

    Order saveOrder(Order order);

    void deleteOrder(Order order);

}