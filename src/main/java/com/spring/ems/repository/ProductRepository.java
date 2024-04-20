package com.spring.ems.repository;

import com.spring.ems.dto.ProductPriceRangeDto;
import com.spring.ems.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT new com.spring.ems.dto.ProductPriceRangeDto(MIN(p.price), MAX(p.price)) FROM Product p")
    ProductPriceRangeDto findPriceRange();

    @Query("SELECT p FROM Product p WHERE p.name LIKE lower(concat('%', :name,'%')) AND (:categoryId = 0 OR p.category.id = :categoryId) AND (:companyId = 0 OR p.company.id = :companyId)")
    List<Product> filter(@Param("name") String name, @Param("categoryId") Long CategoryId,
            @Param("companyId") Long companyId);
    // List<Product> findByNameLikeAndCategoryId(String name, Long categoryId);
}
