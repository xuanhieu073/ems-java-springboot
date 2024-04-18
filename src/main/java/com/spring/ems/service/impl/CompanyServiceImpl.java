package com.spring.ems.service.impl;

import com.spring.ems.entity.Company;
import com.spring.ems.repository.CompanyRepository;
import com.spring.ems.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;
    @Override
    public Company createCompany(Company company) {
        Company savedCompany = companyRepository.save(company);
        return savedCompany;
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }
}
