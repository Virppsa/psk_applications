package com.applications.system.dao;

import com.applications.system.entity.Applicant;
import com.applications.system.service.ApplicantService;

import java.util.List;

public interface ApplicantDAO {
    List<Applicant> findAll();

    Applicant findById(int theId);

    List<Applicant> findByName(String firstName);

    Applicant save(Applicant theApplicant);

    void delete(int theId);


}
