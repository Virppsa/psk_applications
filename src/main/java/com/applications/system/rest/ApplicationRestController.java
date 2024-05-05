package com.applications.system.rest;

import com.applications.system.dto.ApplicationDTO;
import com.applications.system.entity.Application;
import com.applications.system.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationRestController {

    private ApplicationService applicationService;
    //expose the "/applicants" endpoint that will return all the applicants

    public  ApplicationRestController(ApplicationService theApplicationService){
        applicationService = theApplicationService;
    }

    @GetMapping("/applications")
    public List<Application> findAll(){
        return applicationService.findAll();
    }

    @GetMapping("/applications/{theCompanyId}")
    public List<Application> findByCompanyId(@PathVariable int theCompanyId){
        return applicationService.findByCompanyId(theCompanyId);
    }

    @PostMapping("/applications")
    public Application addApplication(@RequestBody ApplicationDTO theApplicationDTO){
        return applicationService.save(theApplicationDTO);
    }

    @DeleteMapping("/applications/{theId}")
    public String deleteApplication(@PathVariable int theId) {
        applicationService.delete(theId);

        return "Application deleted succesfully";
    }
}
