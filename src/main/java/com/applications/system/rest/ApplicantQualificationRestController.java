package com.applications.system.rest;

import com.applications.system.entity.ApplicantQualification;
import com.applications.system.service.ApplicantQualificationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicantQualificationRestController {
    private ApplicantQualificationService applicantQualificationService;

    public  ApplicantQualificationRestController(ApplicantQualificationService theApplicantQualificationService){
        applicantQualificationService = theApplicantQualificationService;
    }

    @GetMapping("/applicants_qualifications")
    public List<ApplicantQualification> findAll(){
        return applicantQualificationService.findAll();
    }
}
