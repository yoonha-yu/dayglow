package com.example.dayglow.Product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public List<Product> getProductByCategory(String category){
        return productRepository.findByCategory(category);
    }

    public List<Product> searchProduct(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
