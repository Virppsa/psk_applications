package com.applications.system.service;

import com.applications.system.dao.ApplicantDAO;
import com.applications.system.entity.Applicant;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantServiceImpl implements ApplicantService{

    private ApplicantDAO applicantDAO;

    @Autowired
    public ApplicantServiceImpl(ApplicantDAO theApplicantDAO){
        applicantDAO = theApplicantDAO;
    }

    @Override
    public List<Applicant> findAll() {
        return applicantDAO.findAll();
    }

    @Override
    public Applicant findById(int theId) {
        return applicantDAO.findById(theId);
    }

    @Transactional
    @Override
    public Applicant save(Applicant theApplicant) {
        return applicantDAO.save(theApplicant);
    }

    @Transactional
    @Override
    public void delete(int theId) {
        applicantDAO.delete(theId);
    }
}
