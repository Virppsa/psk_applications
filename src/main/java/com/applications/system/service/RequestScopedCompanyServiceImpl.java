package com.applications.system.service;

import com.applications.system.entity.Company;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
public class RequestScopedCompanyServiceImpl implements RequestScopedCompanyService{

    @PersistenceContext
    private EntityManager entityManager;

    @Async
    @Transactional
    public CompletableFuture<Void> performAsyncOperationWithEntityManager(int companyId, String description) {
        Company company = entityManager.find(Company.class, companyId);

        if (company != null) {
            company.setDescription(description);
            entityManager.merge(company);
        } else {
            throw new RuntimeException("Company not found");
        }

        return CompletableFuture.completedFuture(null);
    }
}