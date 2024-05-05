package com.applications.system.dao;

import com.applications.system.dto.ApplicationDTO;
import com.applications.system.entity.Applicant;
import com.applications.system.entity.Application;
import com.applications.system.entity.Company;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApplicationDAOJpaImpl implements ApplicationDAO {
    //Defined field
    private EntityManager entityManager;
    private ApplicantDAO applicantDAO;


    //Konstruktorius
    @Autowired
    public ApplicationDAOJpaImpl (EntityManager theEntityManager, ApplicantDAO theApplicantDAO){
        entityManager = theEntityManager;
        applicantDAO = theApplicantDAO;
    }

    @Override
    public List<Application> findAll() {
        //Create query
        TypedQuery<Application> theQuery = entityManager.createQuery("FROM Application", Application.class);

        //Execute and get the result list
        List<Application> applications = theQuery.getResultList();

        //return results
        return applications;
    }

    @Override
    public Application findById(int theId) {
        return entityManager.find(Application.class, theId);
    }

    @Override
    public List<Application> findByCompany(int companyId) {
        TypedQuery<Application> query = entityManager.createQuery(
                "SELECT a FROM Application a WHERE a.company.id = :companyId", Application.class);
        query.setParameter("companyId", companyId);
        return query.getResultList();
    }

    @Override
    public Application save(ApplicationDTO applicationDTO) {
        System.out.println(applicationDTO);
        Applicant applicant = entityManager.find(Applicant.class, applicationDTO.getApplicantId());
        Company company = entityManager.find(Company.class, applicationDTO.getCompanyId());

        Application application = new Application();
        application.setApplicant(applicant);
        application.setCompany(company);

        return entityManager.merge(application);
    }

    @Override
    public void delete(int theId) {
        Application application = entityManager.find(Application.class, theId);
        if (application != null) {
            entityManager.remove(application);
        }
    }
}
