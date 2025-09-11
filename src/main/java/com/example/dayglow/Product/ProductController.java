package com.example.dayglow.Product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductByCategory(@PathVariable String category) {
        return productService.getProductByCategory(category);
    }
    @GetMapping("/search")
    public List<Product> searchProduct(@RequestParam String keyword) {
        return productService.searchProduct(keyword);
    }
    @GetMapping("/{id}")
    public Product getProductDetail(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
