package com.quovantis.recruitment_demo.persistence.repository;

import com.quovantis.recruitment_demo.dto.RecruiterResponseDTO;
import com.quovantis.recruitment_demo.persistence.model.JobApplications;
import com.quovantis.recruitment_demo.persistence.model.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository <JobApplications,Long> {
    @Query(value = "select new com.quovantis.recruitment_demo.dto.RecruiterResponseDTO (application.id,offer.jobTitle,application.email,application.resumeText,application.applicationStatus,application.createdDate) FROM JobOffer  as offer   inner join JobApplications as application on  application.jobOffer.id=offer.id")
    List<RecruiterResponseDTO>  getAllAplication();
       JobApplications findByEmail(String emailId);
      JobApplications findByEmailAndJobOffer(String emailId, JobOffer jobOffer);
}
