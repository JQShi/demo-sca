package org.ben.service.product.service.impl;

import org.ben.model.product.Product;
import org.ben.service.product.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setName("苹果手机-16");
        return product;
    }
}
