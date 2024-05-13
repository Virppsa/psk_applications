package com.applications.system.rest;

import com.applications.system.entity.Company;
import com.applications.system.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompanyRestController {
    private CompanyService companyService;

    public  CompanyRestController(CompanyService theCompanyService){
        companyService = theCompanyService;
    }

    @GetMapping("/companies")
    public List<Company> findAll(){
        return companyService.findAll();
    }

}
