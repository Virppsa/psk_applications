package com.applications.system.dao;

import com.applications.system.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//                                                     Entity type, Primary key
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query("SELECT c FROM Company c WHERE c.name LIKE %:name%")
    List<Company> findByNameContaining(@Param("name") String name);
}
