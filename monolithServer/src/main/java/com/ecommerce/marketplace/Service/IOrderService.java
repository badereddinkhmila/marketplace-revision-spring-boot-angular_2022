package com.ecommerce.marketplace.Service;

import com.ecommerce.marketplace.Entity.Order;

import java.util.Collection;

public interface IOrderService {
    Collection<Order> getAllOrders();

    Order create();

    Order update();
}
