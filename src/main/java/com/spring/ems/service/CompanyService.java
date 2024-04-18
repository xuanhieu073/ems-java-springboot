package com.spring.ems.service;

import com.spring.ems.entity.Company;

import java.util.List;

public interface CompanyService {
    Company createCompany(Company company);
    List<Company> getAll();
}
