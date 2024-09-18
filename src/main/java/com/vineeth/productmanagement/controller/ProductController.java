package com.vineeth.productmanagement.controller;

import com.vineeth.productmanagement.entities.Product;
import com.vineeth.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        return productService.getALlProducts();
    }

    @PostMapping(value = "/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping(value = "/updateProduct/{productID}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long productID) {
        return productService.updateProduct(product,productID);
    }

    @DeleteMapping(value = "/deleteProduct/{productID}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long productID) {
        return productService.deleteProduct(productID);
    }

    @GetMapping(value = "/getAllProductsByCategory/{category}")
    public ResponseEntity<List<Product>> getAllProductsByCategory(@PathVariable String category) {
        return productService.getAllProductsByCategory(category);
    }

}
