package com.vineeth.productmanagement.repository;

import com.vineeth.productmanagement.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM product p WHERE p.product_category= :category", nativeQuery = true)
    List<Product> findAllProductsByCategory(String category);
}
