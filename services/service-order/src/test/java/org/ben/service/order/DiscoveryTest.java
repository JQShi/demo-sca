package org.ben.service.order;

import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@SpringBootTest
public class DiscoveryTest {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private NacosServiceDiscovery nacosServiceDiscovery;

    @Test
    void test() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println("service====>" + service);
            List<ServiceInstance> instances = this.discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                System.out.println("instance====>" + instance.getHost() + ":" + instance.getPort());
            }
        }
    }
}
