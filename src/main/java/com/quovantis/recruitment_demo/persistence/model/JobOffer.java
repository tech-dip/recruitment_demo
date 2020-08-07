package com.quovantis.recruitment_demo.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Entity
public class JobOffer extends  BaseEntity {
    @Column(unique=true)
    private String jobTitle;
    private String NumberOfApplications;   // assuming this field represent  the total aplication required for  any Job Title
    private LocalDate startDate;

    @OneToMany
    private List<JobApplications> jobApplications;

}
