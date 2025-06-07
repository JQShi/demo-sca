package org.ben.service.order.fallback;

import lombok.extern.slf4j.Slf4j;
import org.ben.model.product.Product;
import org.ben.service.order.feign.ProductFeignClient;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductFallback  implements ProductFeignClient {
    @Override
    public Product getProductById(Long id) {
        log.info("fallback method invoked");
        Product product = new Product();
        product.setId(0L);
        product.setName("未知商品");
        return product;
    }
}
