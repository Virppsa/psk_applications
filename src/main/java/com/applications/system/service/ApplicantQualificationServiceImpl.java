package com.applications.system.service;

import com.applications.system.entity.ApplicantQualification;
import com.applications.system.mapper.ApplicantQualificationsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantQualificationServiceImpl implements ApplicantQualificationService {
    private ApplicantQualificationsMapper applicantQualificationsMapper;

    @Autowired
    public ApplicantQualificationServiceImpl(ApplicantQualificationsMapper theApplicantQualificationsMapper){
        applicantQualificationsMapper = theApplicantQualificationsMapper;
    }

    @Override
    public List<ApplicantQualification> findAll() {
        return applicantQualificationsMapper.findAll();
    }

}
