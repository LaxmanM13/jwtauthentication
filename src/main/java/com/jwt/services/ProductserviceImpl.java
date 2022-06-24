package com.jwt.services;


import com.jwt.model.Product;
import com.jwt.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductserviceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductserviceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(String name) {
        return productRepository.findByName(name);
    }



//    @Override
//    public Product save(Product product) {
//        return productRepository.save(product);
//    }

}
