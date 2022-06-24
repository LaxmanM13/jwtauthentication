package com.jwt.services;

import com.jwt.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

    Product getProduct(String name);

    //Product save(Product product);
}
