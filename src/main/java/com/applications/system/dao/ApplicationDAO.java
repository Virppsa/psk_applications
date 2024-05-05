package com.applications.system.dao;

import com.applications.system.dto.ApplicationDTO;
import com.applications.system.entity.Application;
import com.applications.system.entity.Company;

import java.util.List;

public interface ApplicationDAO {
    List<Application> findAll();

    Application findById(int theId);

    List<Application> findByCompany(int companyId);

    Application save(ApplicationDTO application);

    void delete(int theId);
}
