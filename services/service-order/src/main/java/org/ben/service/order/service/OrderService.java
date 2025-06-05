package org.ben.service.order.service;

import org.ben.model.order.Order;

public interface OrderService {

    public Order createOrder(Long productId, Long userId);

}
