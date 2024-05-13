package com.applications.system.service;

import com.applications.system.entity.Applicant;
import com.applications.system.entity.ApplicantQualification;

import java.util.List;

public interface ApplicantQualificationService {
    List<ApplicantQualification> findAll();

    //List<Applicant> findByName(String firstName);
}