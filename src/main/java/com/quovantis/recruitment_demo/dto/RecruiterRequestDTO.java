package com.quovantis.recruitment_demo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class RecruiterRequestDTO {
    private Long id;
    @NotNull
    @Size(min = 2)
    private String jobTitle;
    @NotNull(message = "numberOfApplication can't be null")
    private String numberOfApplications;
    private LocalDate startDate;

}

