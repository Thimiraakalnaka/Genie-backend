package com.example.genie.repo;

import com.example.genie.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM Product WHERE productid=?1", nativeQuery = true)

    Product getProductById(Integer productId);
}
