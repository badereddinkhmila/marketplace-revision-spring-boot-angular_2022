package com.ecommerce.marketplace.Service.Implementation;

import com.ecommerce.marketplace.Entity.Order;
import com.ecommerce.marketplace.Repository.OrderRepository;
import com.ecommerce.marketplace.Service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Collection<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order create() {
        return null;
    }

    @Override
    public Order update() {
        return null;
    }
}
