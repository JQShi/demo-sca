package org.ben.service.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.ben.model.order.Order;
import org.ben.service.order.properties.OrderProperties;
import org.ben.service.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

//@RefreshScope
@RequestMapping("/api/order")
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
    @SentinelResource(value = "seckill-order", fallback = "seckillFallback")
    public Order seckill(@RequestParam Long productId, @RequestParam Long userId) {
        return this.orderService.createOrder(productId, userId);
    }

    public Order seckillFallback(@RequestParam Long productId, @RequestParam Long userId, Throwable ex) {
        log.info("seckill fallback invoked ...");
        Order order = new Order();
        order.setId(0L);
        order.setAmount(BigDecimal.ZERO);
        order.setUserId(userId);
        return order;
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
