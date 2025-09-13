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
        Product existngProduct = productRepository.findById(id).orElseThrow(()-> new RuntimeException("상품을 찾을 수 없습니다. ID" + id));
        existngProduct.setName(updateProduct.getName());
        existngProduct.setCategory(updateProduct.getCategory());
        existngProduct.setDescription(updateProduct.getDescription());
        existngProduct.setPrice(updateProduct.getPrice());
        existngProduct.setStock(updateProduct.getStock());
        existngProduct.setImageUrl(updateProduct.getImageUrl());

        return productRepository.save(existngProduct);
    }
    public void deleteProduct(Long id) {
        Product existingProduct = productRepository.findById(id).orElseThrow(()-> new RuntimeException("상품을 찾을 수 없습니다. ID" + id));
        productRepository.delete(existingProduct);
    }
}
