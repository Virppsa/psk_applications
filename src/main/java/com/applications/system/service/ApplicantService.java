package com.applications.system.service;

import com.applications.system.entity.Applicant;

import java.util.List;

public interface ApplicantService {
    List<Applicant> findAll();

    Applicant findById(int theId);

    Applicant save(Applicant theApplicant);

    void delete(int theId);
}
