package com.example.dayglow.Product;

import com.example.dayglow.Product.ImgSave.FileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final FileService fileService;

    public ProductController(ProductService productService, FileService fileService) {
        this.productService = productService;
        this.fileService = fileService;
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
    public ResponseEntity<Product> getProductDetail(@PathVariable("id") Long id) {
        Product product = productService.getProductById(id);

        if (product.getImageUrl() == null){
            product.setImageUrl("");
        }
        return ResponseEntity.ok(product);
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product updateProduct){
        Product product = productService.updateProduct(id, updateProduct);
        return ResponseEntity.ok(product);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> createProduct(
            @RequestPart("product") Product product,
            @RequestPart("image")MultipartFile imageFile) throws IOException {

        String imageUrl = fileService.save(imageFile);

        product.setImageUrl(imageUrl);

        Product saveProduct = productService.createProduct(product);

        return ResponseEntity.ok(saveProduct);
    }
}
