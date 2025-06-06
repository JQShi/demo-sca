package org.ben.service.order.fallback;

import org.ben.model.product.Product;
import org.ben.service.order.feign.ProductFeignClient;
import org.springframework.stereotype.Component;

@Component
public class ProductFallback  implements ProductFeignClient {
    @Override
    public Product getProductById(Long id) {
        Product product = new Product();
        product.setId(0L);
        product.setName("未知商品");
        return product;
    }
}
