package com.jwt.controller;


import com.jwt.model.Product;
import com.jwt.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/Allproducts")
    public List<Product> getAllProduct() {
        return this.productService.getAllProduct();
    }

    @GetMapping("/Allproducts/{name}")
    public Product getProduct(@PathVariable("name") String name) {
        return this.productService.getProduct(name);
    }

//    @PostMapping("/Allproducts")
//    public Product create(@RequestBody Product product)
//    {
//        return this.productService.save(product);
//    }
}
