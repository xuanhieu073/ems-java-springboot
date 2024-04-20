package com.spring.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
    private Double rating;
    private String description;
    private Boolean freeShip;
    private Long categoryId;
    private Long companyId;
    private List<Long> colorsId;
}
