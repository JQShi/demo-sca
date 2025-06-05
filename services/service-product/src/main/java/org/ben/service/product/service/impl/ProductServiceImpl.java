package org.ben.service.product.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ben.model.product.Product;
import org.ben.service.product.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Value("${server.port}")
    private Long port;

    @Override
    public Product getProductById(Long id) {

        log.info("product service invoked at port:{}", port);

        Product product = new Product();
        product.setId(id);
        product.setName("苹果手机-16");
        return product;
    }
}
