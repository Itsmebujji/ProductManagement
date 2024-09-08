package com.vineeth.productmanagement.service;

import com.vineeth.productmanagement.entities.Product;
import com.vineeth.productmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<List<Product>> getALlProducts() {
        try{
            List<Product> products = productRepository.findAll();
            if(products.isEmpty()){
                return ResponseEntity.badRequest().build();
            }else{
                return new ResponseEntity<>(products, HttpStatus.OK);
            }
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Product> addProduct(Product product) {
        Product newProduct = new Product();
        if(product==null){
            return ResponseEntity.badRequest().build();
        }else{
            try{
                newProduct.setProductId(product.getProductId());
                newProduct.setProductName(product.getProductName());
                newProduct.setProductDescription(product.getProductDescription());
                newProduct.setProductPrice(product.getProductPrice());
                newProduct.setProductImage(product.getProductImage());
                newProduct.setProductCategory(product.getProductCategory());
                productRepository.save(newProduct);
                return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
            }catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
    }

    public ResponseEntity<Product> updateProduct(Product product, Long productID) {
        if(product!=null&&productID!=null){
            try{
                product.setProductName(product.getProductName());
                product.setProductDescription(product.getProductDescription());
                product.setProductPrice(product.getProductPrice());
                product.setProductImage(product.getProductImage());
                product.setProductCategory(product.getProductCategory());
                productRepository.save(product);
                return new ResponseEntity<>(product, HttpStatus.OK);
            }catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }


    public ResponseEntity<Object> deleteProduct(Long productID) {
        if(productID!=null){
            try{
                int id = productID.intValue();
                Product p = productRepository.findById(id).orElse(null);
                if(p!=null){
                    productRepository.deleteById(id);
                    return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
                }
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }catch (Exception e) {
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }

//    public ResponseEntity<Product> getAllProductsByCategory(String category) {
//
//    }
}

