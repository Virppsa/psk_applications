package com.applications.system.service;

import com.applications.system.dao.ApplicantDAO;
import com.applications.system.entity.Applicant;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Service
@Scope(WebApplicationContext.SCOPE_APPLICATION)
public class ApplicantServiceImpl implements ApplicantService{

    private ApplicantDAO applicantDAO;

    @Autowired
    public ApplicantServiceImpl(ApplicantDAO applicantDAO){
        this.applicantDAO = applicantDAO;
    }

    @Override
    public List<Applicant> findAll() {
        return applicantDAO.findAll();
    }

    @Override
    public Applicant findById(int theId) {
        return applicantDAO.findById(theId);
    }

    @Transactional //Automatine db tranzakcija
    @Override
    public Applicant save(Applicant theApplicant) {
//        if(theApplicant.getFirstName() == null){
//            throw new RuntimeException("First name cannot be null");
//        }
        theApplicant.setFirstName("Applicant " + theApplicant.getFirstName());

        return applicantDAO.save(theApplicant);
    }

    @Transactional
    @Override
    public void delete(int theId) {
        applicantDAO.delete(theId);
    }

    @Override
    public List<Applicant> findByName(String firstName) {
        return applicantDAO.findByName(firstName);
    }
}
