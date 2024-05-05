package com.applications.system.service;

import com.applications.system.dto.ApplicationDTO;
import com.applications.system.entity.Application;

import java.util.List;

public interface ApplicationService {
    List<Application> findAll();

    List<Application> findByCompanyId(int companyId);

    Application save(ApplicationDTO theApplication);

    void delete(int theId);
}
