package com.applications.system.dao;

import com.applications.system.entity.Company;

import java.util.List;

public interface CompanyDAO {
    List<Company> findAll();

    Company findById(int theId);

    Company save(Company theCompany);

    void delete(int theId);
}
