package org.ben.service.order.service.impl;

import org.ben.model.order.Order;
import org.ben.model.product.Product;
import org.ben.service.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Order createOrder(Long productId, Long userId) {
        Product product = this.getProductFromRemote(productId);
        Order order = new Order();
        order.setId(1L);
        order.setAmount(BigDecimal.TEN);
        order.setUserId(userId);
        order.setProductList(Arrays.asList(product));
        return order;
    }

    private Product getProductFromRemote(Long productId) {
        String serviceId = "service-product";
        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
        int port = instances.get(0).getPort();
        String host = instances.get(0).getHost();

        String url = "http://" + host + ":" + port + "/product/" + productId;
        Product product = this.restTemplate.getForObject(url, Product.class);
        return product;
    }

}
