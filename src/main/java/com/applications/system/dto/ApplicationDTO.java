package com.applications.system.dto;

public class ApplicationDTO {
    private int companyId;
    private int applicantId;

    public ApplicationDTO() {
    }

    public ApplicationDTO(int companyId, int applicantId) {
        this.companyId = companyId;
        this.applicantId = applicantId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    @Override
    public String toString() {
        return "ApplicationDTO{" +
                "companyId=" + companyId +
                ", applicantId=" + applicantId +
                '}';
    }
}


