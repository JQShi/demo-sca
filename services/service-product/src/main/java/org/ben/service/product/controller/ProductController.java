package org.ben.service.product.controller;


import org.ben.model.product.Product;
import org.ben.service.product.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/product")
@RestController
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/product/{id}")
    public Product getProduct(@PathVariable("id") Long id) throws InterruptedException {
        return this.productService.getProductById(id);
    }

}
