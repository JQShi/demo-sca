package org.ben.service.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.ben.model.order.Order;
import org.ben.service.order.properties.OrderProperties;
import org.ben.service.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RefreshScope
@RestController
@Slf4j
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

    @GetMapping("/seckill")
    public Order seckill(@RequestParam Long productId, @RequestParam Long userId) {
        return this.orderService.createOrder(productId, userId);
    }

    @GetMapping("/writedb")
    public String writeDb() {
        log.info("write db ...");
        return "writeDb";
    }

    @GetMapping("/readdb")
    public String readDb() {
        log.info("read db ...");
        return "readDb";
    }

}
