package com.applications.system.rest;

import com.applications.system.entity.Applicant;
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



//    @GetMapping("/find_qal_by_name/{firstName}")
//    public List<Applicant> findByName(@PathVariable String firstName){
//        return applicantQualificationService.findByName(firstName);
//    }
}
