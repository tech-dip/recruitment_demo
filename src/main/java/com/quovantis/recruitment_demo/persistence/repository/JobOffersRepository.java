package com.quovantis.recruitment_demo.persistence.repository;

import com.quovantis.recruitment_demo.persistence.model.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOffersRepository extends JpaRepository<JobOffer, Long> {
    JobOffer findByJobTitle(String jobTitle);

}
