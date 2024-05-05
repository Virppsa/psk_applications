package com.applications.system.service;

import com.applications.system.dao.ApplicationDAO;
import com.applications.system.dto.ApplicationDTO;
import com.applications.system.entity.Application;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationDAO applicationDAO;

    @Autowired
    public ApplicationServiceImpl(ApplicationDAO theApplicationDAO){
        applicationDAO = theApplicationDAO;
    }

    @Override
    public List<Application> findAll() {
        return applicationDAO.findAll();
    }

    @Override
    public List<Application> findByCompanyId(int theCompanyId) {
        return applicationDAO.findByCompany(theCompanyId);
    }

    @Transactional
    @Override
    public Application save(ApplicationDTO theApplication) {
        return applicationDAO.save(theApplication);
    }

    @Transactional
    @Override
    public void delete(int theId) {
        applicationDAO.delete(theId);
    }
}
