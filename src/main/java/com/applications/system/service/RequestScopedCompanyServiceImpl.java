package com.applications.system.service;

import com.applications.system.entity.Company;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.enterprise.context.RequestScoped;
import java.sql.Time;
import java.util.concurrent.CompletableFuture;

@Service
public class RequestScopedCompanyServiceImpl implements RequestScopedCompanyService{

    //Galime naudoti, nes springbootas handlina, bet tai nėra gera praktika su async,
    // nes asyc metodas beveik visada ilgesnis nei http request.
    @PersistenceContext
    @RequestScoped // nereiketu naudoti su asynchroniniais metodais kuriu galo nelaukiame
    private EntityManager entityManager;

    @Async
    @Transactional
    public CompletableFuture<Void> performAsyncOperationWithEntityManager(int companyId, String description) {
        System.out.println("UPDATE IS RUNNING ---------------------");
        Company company = entityManager.find(Company.class, companyId);

        try {
            // Simulating a delay to increase the likelihood of a version conflict
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        if (company != null) {
            company.setDescription(description);
            entityManager.merge(company);
        } else {
            throw new RuntimeException("Company not found");
        }

        return CompletableFuture.completedFuture(null);
    }
}