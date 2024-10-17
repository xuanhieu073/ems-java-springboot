package com.spring.ems.service;

import com.spring.ems.dto.ProductDto;
import com.spring.ems.dto.ProductPriceRangeDto;
import com.spring.ems.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDto productDto);

    List<ProductDto> getAll();

    ProductPriceRangeDto getPriceRange();

    List<ProductDto> filter(String name, Long CategoryId, Long companyId, Long colorId, Long fromPrice, Long toPrice,
            Boolean isFreeShip);

    ProductDto getProductById(Long Id);

    ProductDto updateProduct(Long productId, ProductDto productDto);

    void deleteProduct(Long productId);
}
