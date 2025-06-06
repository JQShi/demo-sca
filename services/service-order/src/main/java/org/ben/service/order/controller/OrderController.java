package org.ben.service.order.controller;

import org.ben.model.order.Order;
import org.ben.service.order.properties.OrderProperties;
import org.ben.service.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RefreshScope
@RestController
public class OrderController {

//    @Value("${order.timeout}")
//    private String orderTimeout;
//
//    @Value("${order.auto-confirm}")
//    private String orderAutoConfirm;

    @Autowired
    private OrderProperties orderProperties;

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order/config")
    public String getConfig() {
        return this.orderProperties.getTimeout() + " : " + this.orderProperties.getAutoConfirm() + ":" + this.orderProperties.getDbUrl();
    }


    @GetMapping("/order/create")
    public Order createOrder(@RequestParam Long productId, @RequestParam Long userId) {
        return this.orderService.createOrder(productId, userId);
    }

}
