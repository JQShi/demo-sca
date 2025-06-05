package org.ben.service.order.controller;

import org.ben.model.order.Order;
import org.ben.service.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order/create")
    public Order createOrder(@RequestParam Long productId, @RequestParam Long userId) {
        return this.orderService.createOrder(productId, userId);
    }

}
