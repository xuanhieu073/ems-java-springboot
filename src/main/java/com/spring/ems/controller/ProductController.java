package com.spring.ems.controller;

import com.spring.ems.dto.ProductDto;
import com.spring.ems.dto.ProductPriceRangeDto;
import com.spring.ems.entity.Product;
import com.spring.ems.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        Product newProduct = productService.createProduct(productDto);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/price-range")
    public ResponseEntity<ProductPriceRangeDto> getPriceRange() {
        ProductPriceRangeDto priceRange = productService.getPriceRange();
        return ResponseEntity.ok(priceRange);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductDto>> filter(@RequestParam String query,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long companyId) {
        List<ProductDto> products = productService.filter(query, categoryId, companyId);
        return ResponseEntity.ok(products);
    }
}
