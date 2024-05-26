package com.applications.system.rest;

import com.applications.system.entity.Company;
import com.applications.system.service.CompanyService;
import com.applications.system.service.LongRunningService;
import com.applications.system.service.RequestScopedCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api")
public class CompanyRestController {

    //@Autowired
    private CompanyService companyService;

    //@Autowired
    private LongRunningService longRunningService;

    private RequestScopedCompanyService requestScopedCompanyService;

    @Autowired
    public  CompanyRestController(CompanyService theCompanyService, LongRunningService theLongRunningService, RequestScopedCompanyService theRequestScopedCompanyService){
        companyService = theCompanyService;
        longRunningService = theLongRunningService;
        requestScopedCompanyService = theRequestScopedCompanyService;
    }

    @GetMapping("/companies")
    public List<Company> findAll(){
        return companyService.findAll();
    }

    @GetMapping("/companies/{searchName}")
    public List<Company> findByName(@PathVariable String searchName){
        return companyService.findByName(searchName);
    }

    @PutMapping("/companies/{randomString}")
    public void addRandomStringCompDescription(@PathVariable String randomString){
        companyService.editCompanyDescription(randomString);
    }

    @PutMapping("/companies/edit_by_id/{theId}")
    public void editCompanyById(@PathVariable int theId){
        companyService.editCompanyById(theId);
    }

    @PostMapping("/companies")
    public void createCompany(@RequestBody Company theCompany) {
        companyService.createCompany(theCompany);
    }

    // Sitas endpointas yra skirtas kad patestuoti optimistic lock.
    @PutMapping("/companies/test_optimistic_lock_exception")
    public String testConcurrentUpdate(@RequestParam int companyId) {
        Runnable task = () -> {
            try {
                companyService.editCompanyById(companyId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Concurrent update test completed";
    }

    //2 punktas thread.sleep
    @GetMapping("/start-calculation")
    public CompletableFuture<String> startCalculation() {
        return longRunningService.performLongComputation();
    }

    @GetMapping("/companies/update-description-request-scoped")
    public CompletableFuture<Void> updateDescriptionRequestScoped(@RequestParam int companyId, @RequestParam String description) {
        return requestScopedCompanyService.performAsyncOperationWithEntityManager(companyId, description);
    }
}
