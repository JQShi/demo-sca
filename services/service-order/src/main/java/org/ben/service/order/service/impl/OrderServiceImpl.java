package org.ben.service.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.ben.model.order.Order;
import org.ben.model.product.Product;
import org.ben.service.order.feign.ProductFeignClient;
import org.ben.service.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {


    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private ProductFeignClient productFeignClient;


    public Order createOrderFallback(Long productId, Long userId, BlockException ex) {
        Order order = new Order();
        order.setId(0L);
        order.setAmount(BigDecimal.ZERO);
        order.setUserId(userId);
        return order;
    }

    @SentinelResource(value = "create-order", blockHandler = "createOrderFallback")
    @Override
    public Order createOrder(Long productId, Long userId) {
//        Product product = this.getProductFromRemoteWithLoadBalancedAnnotation(productId);

        Product product = this.productFeignClient.getProductById(productId);
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

    private Product getProductFromRemoteWithLoadBalance(Long productId) {
        String serviceId = "service-product";
        ServiceInstance service = this.loadBalancerClient.choose(serviceId);
        String host = service.getHost();
        int port = service.getPort();

        String url = "http://" + host + ":" + port + "/product/" + productId;
        log.info("invoked url {}", url);
        Product product = this.restTemplate.getForObject(url, Product.class);
        return product;
    }

    private Product getProductFromRemoteWithLoadBalancedAnnotation(Long productId) {
        String serviceId = "service-product";
        String url = "http://" + serviceId + "/product/" + productId;
        log.info("invoked url {}", url);
        log.info("restTemplate:" + this.restTemplate);
        Product product = this.restTemplate.getForObject(url, Product.class);
        return product;
    }

}
