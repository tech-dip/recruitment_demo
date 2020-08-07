package com.quovantis.recruitment_demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
public class RecruiterResponseDTO {
    private Long  applicationId;
    private String jobTitle;
    private String email;
    private String resumeText;
    private String applicationStatus;
    private LocalDate appliedDate;
    private String NumberOfApplications;


    public RecruiterResponseDTO(Long applicationId, String jobTitle, String email, String resumeText, String applicationStatus, LocalDate appliedDate) {
        this.applicationId = applicationId;
        this.jobTitle = jobTitle;
        this.email = email;
        this.resumeText = resumeText;
        this.applicationStatus = applicationStatus;
        this.appliedDate = appliedDate;
    }
}
