package com.quovantis.recruitment_demo.persistence.model;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.quovantis.recruitment_demo.persistence.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Setter
@Getter
@Entity
public class JobApplications extends  BaseEntity {

    private String email;
    private String resumeText;
    private String applicationStatus;

    @ManyToOne
    private JobOffer jobOffer;


}
