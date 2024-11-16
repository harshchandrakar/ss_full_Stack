package com.harsh.yummy.repo;

import com.harsh.yummy.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product,Long> {
    Optional<Product> findById(UUID id);

    @Query("SELECT p FROM Product p WHERE CAST(p.price AS float) BETWEEN :minPrice AND :maxPrice ORDER BY CAST(p.price AS float) DESC")
    List<Product> findTopProductsInRange(@Param("minPrice") float minPrice, @Param("maxPrice") float maxPrice);
}
