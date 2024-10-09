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

    @Query("SELECT p FROM Product p JOIN p.colors c WHERE (:name IS NULL OR p.name LIKE lower(concat('%', :name,'%'))) AND (:categoryId IS NULL OR p.category.id = :categoryId) AND (:companyId IS NULL OR p.company.id = :companyId) AND (:colorId IS NULL OR c.id = :colorId) AND (:fromPrice IS NULL OR p.price >= :fromPrice) AND (:toPrice IS NULL OR p.price <= :toPrice) AND (:isFreeShip IS NULL OR p.freeShip = :isFreeShip) ORDER BY p.price ASC")
    List<Product> filter(@Param("name") String name, @Param("categoryId") Long CategoryId,
        @Param("companyId") Long companyId, @Param("colorId") Long colorId, @Param("fromPrice") Long fromPrice, @Param("toPrice") Long toPrice,
        @Param("isFreeShip") Boolean isFreeShip);
    // List<Product> findByNameLikeAndCategoryId(String name, Long categoryId);
}
