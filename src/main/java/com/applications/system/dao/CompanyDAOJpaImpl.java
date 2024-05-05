package com.applications.system.dao;

import com.applications.system.entity.Applicant;
import com.applications.system.entity.Company;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDAOJpaImpl implements CompanyDAO {

    private EntityManager entityManager;

    //Konstruktorius
    @Autowired
    public CompanyDAOJpaImpl (EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public List<Company> findAll() {
        //Create query
        TypedQuery<Company> theQuery = entityManager.createQuery("FROM Company", Company.class );

        return theQuery.getResultList();
    }

    @Override
    public Company findById(int theId) {
        return entityManager.find(Company.class, theId);
    }

    @Override
    public Company save(Company theCompany) {
        return entityManager.merge(theCompany);
    }

    @Override
    public void delete(int theId) {
        //find the applicant
        Company theCompany = entityManager.find(Company.class, theId);

        //delete the applicant
        entityManager.remove(theCompany);
    }
}
