package com.spring.ems.service.impl;

import com.spring.ems.dto.ProductDto;
import com.spring.ems.dto.ProductPriceRangeDto;
import com.spring.ems.entity.Category;
import com.spring.ems.entity.Color;
import com.spring.ems.entity.Company;
import com.spring.ems.entity.Product;
import com.spring.ems.exception.EmsAPIException;
import com.spring.ems.repository.CategoryRepository;
import com.spring.ems.repository.ColorRepository;
import com.spring.ems.repository.CompanyRepository;
import com.spring.ems.repository.ProductRepository;
import com.spring.ems.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
        private ProductRepository productRepository;
        private CategoryRepository categoryRepository;
        private CompanyRepository companyRepository;
        private ColorRepository colorRepository;
        private ModelMapper modelMapper;

        @Override
        public Product createProduct(ProductDto productDto) {
                Product newProduct = modelMapper.map(productDto, Product.class);
                Category productCategory = categoryRepository.findById(productDto.getCategoryId())
                                .orElseThrow(() -> new EmsAPIException(HttpStatus.BAD_REQUEST,
                                                "Category is not exists!"));
                newProduct.setCategory(productCategory);
                Company productCompary = companyRepository.findById(productDto.getCompanyId())
                                .orElseThrow(() -> new EmsAPIException(HttpStatus.BAD_REQUEST,
                                                "Company is not exitst!"));
                newProduct.setCompany(productCompary);
                Set<Color> productColors = productDto.getColorsId().stream()
                                .map(colorId -> colorRepository.findById(colorId)
                                                .orElseThrow(() -> new EmsAPIException(HttpStatus.BAD_REQUEST,
                                                                "Color is exists!")))
                                .collect(Collectors.toSet());
                newProduct.setColors(productColors);
                productRepository.save(newProduct);
                return newProduct;
        }

        public List<ProductDto> getAll() {
                List<Product> products = productRepository.findAll();
                return products.stream().map(product -> modelMapper.map(product, ProductDto.class))
                                .collect(Collectors.toList());
        }

        @Override
        public ProductPriceRangeDto getPriceRange() {
                ProductPriceRangeDto priceRange = productRepository.findPriceRange();
                return priceRange;
        }

        @Override
        public List<ProductDto> filter(String name, Long categoryId, Long companyId, Long colorId, Long fromPrice, Long toPrice, Boolean isFreeShip) {
                List<Product> products = productRepository.filter(name, categoryId, companyId, colorId, fromPrice, toPrice, isFreeShip);
                return products.stream().map(product -> modelMapper.map(product, ProductDto.class))
                                .collect(Collectors.toList());
        }

}
