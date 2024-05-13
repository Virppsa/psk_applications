package com.applications.system.rest;



import com.applications.system.entity.Applicant;
import com.applications.system.service.ApplicantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicantRestController {

//    inject applicant dao (use constructor injection)
//    private ApplicantDAO applicantDAO;

    private ApplicantService applicantService;
    //expose the "/applicants" endpoint that will return all the applicants

    public  ApplicantRestController(ApplicantService theApplicantService){
        applicantService = theApplicantService;
    }

    @GetMapping("/applicants")
    public List<Applicant> findAll(){
        return applicantService.findAll();
    }

    @GetMapping("applicants/{theId}")
    public Applicant findApplicantById(@PathVariable int theId){
        Applicant theApplicant = applicantService.findById(theId);

        if(theApplicant == null){
            throw new RuntimeException("Could not find applicant: " + theId);
        }

        return theApplicant;
    }

    @PostMapping("/applicants")
    public Applicant addApplicant(@RequestBody Applicant theApplicant){
//        theApplicant.setId(0);
        Applicant dbApplicant = applicantService.save(theApplicant);
        return dbApplicant;
    }

    @DeleteMapping("/applicants/{theId}")
    public String deleteEmployee(@PathVariable int theId) {
        applicantService.delete(theId);

        return "User deleted succesfully";
    }


    @GetMapping("/find_by_name/{firstName}")
    public List<Applicant> findByName(@PathVariable String firstName){
         return applicantService.findByName(firstName);
    }
}
