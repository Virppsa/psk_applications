package com.applications.system.dao;

import com.applications.system.entity.Applicant;
import com.applications.system.entity.Qualification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class ApplicantDAOJpaImpl implements ApplicantDAO{
    //Defined field
    private EntityManager entityManager;


    //Konstruktorius
    @Autowired
    public ApplicantDAOJpaImpl (EntityManager theEntityManager){
        entityManager = theEntityManager;
    }


    @Override
    public List<Applicant> findAll() {
        //Create query
        TypedQuery<Applicant> theQuery = entityManager.createQuery("FROM Applicant", Applicant.class );

        //Execute and get the result list
        List<Applicant> applicants = theQuery.getResultList();

        //return results
        return applicants;
    }

    @Override
    public Applicant findById(int theId) {
        return entityManager.find(Applicant.class, theId);
    }

    @Override
    public List<Applicant> findByName(String firstName) {
        TypedQuery<Applicant> theQuery = entityManager.createQuery("FROM Applicant WHERE firstName = :firstName", Applicant.class);

        // Set the parameter value to the method's firstName parameter
        theQuery.setParameter("firstName", firstName);
        //Execute and get the result list
        List<Applicant> applicants = theQuery.getResultList();

        //return results
        return applicants;
    }

    @Override
    public Applicant save(Applicant theApplicant) {
        if (theApplicant.getQualifications() != null) {
            Set<Qualification> managedQualifications = new HashSet<>();
            for (Qualification qualification : theApplicant.getQualifications()) {
                if (qualification.getId() != 0) {
                    // Fetch existing qualification to ensure data integrity
                    Qualification existingQualification = entityManager.find(Qualification.class, qualification.getId());
                    managedQualifications.add(existingQualification);
                } else {
                    // New qualification, persist it
                    entityManager.persist(qualification);
                    managedQualifications.add(qualification);
                }
            }
            theApplicant.setQualifications(managedQualifications);
        }
        return entityManager.merge(theApplicant);
    }

    @Override //čia jau tranzakcijų nereikia nes jos daromos in the service layer
    public void delete(int theId) {
        //find the applicant
        Applicant theApplicant = entityManager.find(Applicant.class, theId);

        //delete the applicant
        entityManager.remove(theApplicant);
    }
}
