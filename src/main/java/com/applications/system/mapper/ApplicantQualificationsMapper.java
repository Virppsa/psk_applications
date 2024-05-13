package com.applications.system.mapper;

import com.applications.system.entity.ApplicantQualification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ApplicantQualificationsMapper {
    @Select("SELECT * FROM applicants_qualifications;") //Nėra coupling in MyBitis
    List<ApplicantQualification> findAll();
}
