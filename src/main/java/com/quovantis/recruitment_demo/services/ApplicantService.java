package com.quovantis.recruitment_demo.services;

import com.quovantis.recruitment_demo.common.response.ResponseDTO;
import com.quovantis.recruitment_demo.dto.ApplicantRequestDTO;
import com.quovantis.recruitment_demo.dto.RecruiterResponseDTO;
import com.quovantis.recruitment_demo.persistence.ApplicationStatus;
import com.quovantis.recruitment_demo.persistence.model.JobApplications;
import com.quovantis.recruitment_demo.persistence.model.JobOffer;
import com.quovantis.recruitment_demo.persistence.repository.JobApplicationRepository;
import com.quovantis.recruitment_demo.persistence.repository.JobOffersRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ApplicantService {

    @Autowired
    JobOffersRepository jobOffersRepository;
    @Autowired
    JobApplicationRepository jobApplicationRepository;

    public ResponseDTO applyForJob(ApplicantRequestDTO applicantRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        JobOffer jobOffer = jobOffersRepository.findById(applicantRequestDTO.getJobOfferId()).orElseThrow(() -> new NullPointerException("Job Offer not found for the id " + applicantRequestDTO.getJobOfferId()));
        responseDTO = isAlreadyApplied(applicantRequestDTO.getEmail(), jobOffer);
        if (responseDTO.isSuccess()) {
            return responseDTO;
        }
        JobApplications jobApplications = new JobApplications();
        jobApplications.setEmail(applicantRequestDTO.getEmail());
        jobApplications.setResumeText(applicantRequestDTO.getResumeText());
        jobApplications.setJobOffer(jobOffer);
        jobApplications.setApplicationStatus(ApplicationStatus.APPLIED.toString());
        try {
            Long applicationId = jobApplicationRepository.save(jobApplications).getId();
            if (applicationId != null) {
                responseDTO.setSuccess(true);
                responseDTO.setData(applicationId);
                responseDTO.setMessage("Applied  successfully");
            }
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                if (((ConstraintViolationException) e.getCause()).getSQLException().getMessage().contains("Duplicate")) {
                    responseDTO.setMessage("Email Id Already Exist !");
                }
            }
            responseDTO.setSuccess(false);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }


    public ResponseDTO jobAppliedByApplicant(String email) {
        ResponseDTO responseDTO = new ResponseDTO();
            List<RecruiterResponseDTO> applicationsList = jobApplicationRepository.getAllAplication().stream().filter(it->email.equals(it.getEmail())).collect(Collectors.toList());
            if (Objects.nonNull(applicationsList) && applicationsList.size() > 0) {
                responseDTO.setSuccess(true);
                responseDTO.setData(applicationsList);
                responseDTO.setMessage("Applied Jobs Fetched Successfully");
            } else {
                responseDTO.setSuccess(true);
                responseDTO.setData(applicationsList);
                responseDTO.setMessage("No Applied Jobs Found");
            }
            return responseDTO;
    }


    public ResponseDTO updateApplicationStatus(ApplicantRequestDTO applicantRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        boolean  isPresent  = Arrays.stream(ApplicationStatus.values()).filter(it-> it.name().equalsIgnoreCase(applicantRequestDTO.getApplicationStatus())).findAny().isPresent();
        if (isPresent) {
            JobApplications jobApplications = jobApplicationRepository.findById(applicantRequestDTO.getId()).orElseThrow(() -> new NullPointerException("Application  not found for the id "+ applicantRequestDTO.getId())) ;
            if (Objects.nonNull(jobApplications)) {
                jobApplications.setApplicationStatus(applicantRequestDTO.getApplicationStatus());
                jobApplicationRepository.save(jobApplications);
                responseDTO.setSuccess(true);
                responseDTO.setMessage("Application Status Updated!");
                responseDTO.setData(jobApplications);
            }
        } else {
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Invalid Status  entered!");
        }
        return responseDTO;
    }

    private ResponseDTO isAlreadyApplied(String email, JobOffer jobOffer) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (jobApplicationRepository.findByEmailAndJobOffer(email, jobOffer) != null) {
            responseDTO.setMessage("Already Applied");
            responseDTO.setSuccess(true);
        } else {
            responseDTO.setSuccess(false);
        }
        return responseDTO;
    }

}
