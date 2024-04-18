package com.spring.ems.repository;

import com.spring.ems.dto.ProductPriceRangeDto;
import com.spring.ems.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT new com.spring.ems.dto.ProductPriceRangeDto(MIN(p.price), MAX(p.price)) FROM Product p")
    ProductPriceRangeDto findPriceRange();
}
