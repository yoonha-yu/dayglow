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
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("상품이 존재하지 않습니다. ID:" + id));
    }
    public Product createProduct(Product product) {
        product.setName(product.getName());
        product.setCategory(product.getCategory());
        product.setDescription(product.getDescription());
        product.setPrice(product.getPrice());
        product.setStock(product.getStock());
        product.setImageUrl(product.getImageUrl());

        return productRepository.save(product);
    }
    public Product updateProduct(Long id, Product updateProduct) {
        Product existingProduct = productRepository.findById(id).orElseThrow(()-> new RuntimeException("상품을 찾을 수 없습니다. ID" + id));
        existingProduct.setName(updateProduct.getName());
        existingProduct.setCategory(updateProduct.getCategory());
        existingProduct.setDescription(updateProduct.getDescription());
        existingProduct.setPrice(updateProduct.getPrice());
        existingProduct.setStock(updateProduct.getStock());
        existingProduct.setImageUrl(updateProduct.getImageUrl());

        return productRepository.save(existingProduct);
    }
    public void deleteProduct(Long id) {
        Product existingProduct = productRepository.findById(id).orElseThrow(()-> new RuntimeException("상품을 찾을 수 없습니다. ID" + id));
        productRepository.delete(existingProduct);
    }
}
