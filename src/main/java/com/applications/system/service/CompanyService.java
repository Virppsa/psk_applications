package com.applications.system.service;

import com.applications.system.entity.Company;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CompanyService {
    List<Company> findAll();
    Company findById(int id);

    List<String> findAllNames();

    List<Integer> findAllIds();

    List<Company> findByName(String searchName);

    void editCompanyDescription(String randomString);

    void createCompany(Company theCompany);

    void editCompanyById(int theId);

    void recover(ObjectOptimisticLockingFailureException e, String randomString);

    void recover(ObjectOptimisticLockingFailureException e, int theId);

    CompletableFuture<Void> updateCompanyDescriptionAsync(int companyId, String description);

}
