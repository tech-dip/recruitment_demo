package com.quovantis.recruitment_demo.dto;

import com.quovantis.recruitment_demo.persistence.ApplicationStatus;
import com.quovantis.recruitment_demo.persistence.model.JobOffer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class ApplicantRequestDTO {

    private Long id;
    private  String applicationStatus;
    @NotBlank
    @Size(min = 10)
    private String email;
    @NotBlank
    @Size(min = 10)
    private String resumeText;
    @NotNull
    private Long  jobOfferId;

}
