package org.ben.service.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

@SpringBootTest
public class LoadBalanceTest {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Test
    void test() {
        for (int i = 0; i < 5; i++) {
            ServiceInstance service = this.loadBalancerClient.choose("service-product");
            System.out.println(service.getHost() + "" + service.getPort());
        }

    }
}
