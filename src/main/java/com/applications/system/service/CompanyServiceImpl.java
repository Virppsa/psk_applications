package com.applications.system.service;

import com.applications.system.dao.CompanyRepository;
import com.applications.system.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService{
    //private CompanyDAO companyDAO;
    private CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository theCompanyRepository){
        companyRepository = theCompanyRepository;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findById(int theId){
        Optional<Company> result = companyRepository.findById(theId);

        Company theCompany = null;

        if (result.isPresent()){
            theCompany = result.get();
        } else {
            throw new RuntimeException("Did not find company by id - " + theId);
        }

        return theCompany;
    }

    @Override
    public List<String> findAllNames(){
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(Company::getName)
                .collect(Collectors.toList());
    }

    @Override
    public  List<Integer> findAllIds(){
        List<Company> companies = companyRepository.findAll();
        return companies.stream().map(Company::getId).collect(Collectors.toList());
    }

    //gauna kompanijas kurių pavadinimas prasideda kaip kažkoks stringas
    @Override
    public List<Company> findByName(String searchName){
        List<Company> result = companyRepository.findByNameContaining(searchName);

        return result;
    }


    //Prie visu kompaniju descriptions prides zodi {kazkoki} ir issaugos db
    @Override
    @Transactional
    @Retryable(
            value = {ObjectOptimisticLockingFailureException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 500)
    )
    public void editCompanyDescription(String randomString){
        List<Company> companies = companyRepository.findAll();

//        Try catch yra automatiskas kadangi naudojame @retryable
//        companies.forEach(company -> {
//            try {
//                company.setDescription(company.getDescription() + randomString);
//                companyRepository.save(company);
//            } catch (OptimisticLockingFailureException e) {
//                // Handle the optimistic locking exception (e.g., retry, log, or notify user)
//                System.out.println("Optimistic locking failure: " + e.getMessage());
//            }
//        });

        companies.forEach(company -> {
            company.setDescription(company.getDescription() + randomString);
            companyRepository.save(company);
        });

    }


    @Override
    @Transactional
    @Retryable(
            value = {ObjectOptimisticLockingFailureException.class},
            maxAttempts = 2,
            backoff = @Backoff(delay = 10)
    )
    public void editCompanyById(int theId){
        System.out.println("Trying to retry");
        try {
            // Simulating a delay to increase the likelihood of a version conflict
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        Optional<Company> result = companyRepository.findById(theId);

        Company theCompany = null;

        if (result.isPresent()) {
            theCompany = result.get();
            theCompany.setDescription(theCompany.getDescription() + " kažkas");
            companyRepository.save(theCompany);
        } else {
            throw new RuntimeException("Did not find company by id - " + theId);
        }
    }

    //2 Punktas. Thread.sleep
    @Async
    @Transactional
    @Retryable(
            value = {ObjectOptimisticLockingFailureException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 500)
    )
    public CompletableFuture<Void> updateCompanyDescriptionAsync(int companyId, String description) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);

        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setDescription(description);
            companyRepository.save(company);
        } else {
            throw new RuntimeException("Company not found");
        }

        return CompletableFuture.completedFuture(null);
    }


    @Override
    public void createCompany(Company theCompany){
        companyRepository.save(theCompany);
    }


    @Recover
    public void recover(ObjectOptimisticLockingFailureException e, String randomString) {
        System.out.println("Failed to update company description after multiple attempts: " + e.getMessage());
    }

    @Recover
    public void recover(ObjectOptimisticLockingFailureException e, int theId) {
        System.out.println("Failed to update company by id after multiple attempts: " + e.getMessage());
    }

    //Nereikia niekur naudoti transactional dėl to, nes JpaRepository provides this with no use
}
