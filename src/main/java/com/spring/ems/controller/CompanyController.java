package com.spring.ems.controller;

import com.spring.ems.entity.Company;
import com.spring.ems.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@AllArgsConstructor
public class CompanyController {
    private CompanyService companyService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        Company newCompany = companyService.createCompany(company);
        return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Company>> getAll() {
        List<Company> companies = companyService.getAll();
        return ResponseEntity.ok(companies);
    }
}
