package com.applications.system.service;

import com.applications.system.dao.CompanyDAO;
import com.applications.system.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{
    private CompanyDAO companyDAO;

    @Autowired
    public CompanyServiceImpl(CompanyDAO theCompanyDAO){
        companyDAO = theCompanyDAO;
    }


    @Override
    public List<Company> findAll() {
        return companyDAO.findAll();
    }
}
