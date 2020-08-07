package com.quovantis.recruitment_demo.services;

import com.quovantis.recruitment_demo.common.response.ResponseDTO;
import com.quovantis.recruitment_demo.dto.RecruiterRequestDTO;
import com.quovantis.recruitment_demo.dto.RecruiterResponseDTO;
import com.quovantis.recruitment_demo.persistence.model.JobOffer;
import com.quovantis.recruitment_demo.persistence.repository.JobApplicationRepository;
import com.quovantis.recruitment_demo.persistence.repository.JobOffersRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RecruiterService {
    @Autowired
    JobOffersRepository jobOffersRepository;
    @Autowired
    JobApplicationRepository jobApplicationRepository;


    public ResponseDTO createOrUpdateJobOffer(RecruiterRequestDTO recruiterRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        JobOffer jobOffer = null;
        if (Objects.nonNull(recruiterRequestDTO.getId())) {
            jobOffer = jobOffersRepository.findById(recruiterRequestDTO.getId()).orElseThrow(() -> new NullPointerException("Job Offer not fond for the id " + recruiterRequestDTO.getId()));
        } else {
            jobOffer = new JobOffer();
        }
        BeanUtils.copyProperties(recruiterRequestDTO, jobOffer);
        jobOffer.setStartDate(LocalDate.now());
        try {
            Long  offerId = jobOffersRepository.save(jobOffer).getId();
            if(offerId !=null) {
                responseDTO.setSuccess(true);
                responseDTO.setData(offerId);
                responseDTO.setMessage("Offer created successfully");
            }
        } catch (Exception e) {
            if(e instanceof DataIntegrityViolationException) {
                if (((ConstraintViolationException) e.getCause()).getSQLException().getMessage().contains("Duplicate")) {
                        responseDTO.setSuccess(false);
                        responseDTO.setMessage("Job Title Already Exist !");
                }
            }
            e.printStackTrace();
        }
        return responseDTO;
    }


    public ResponseDTO getOffersList() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<JobOffer> jobOffersList = jobOffersRepository.findAll();
        if (Objects.nonNull(jobOffersList) && jobOffersList.size()>0) {
            responseDTO = new ResponseDTO();
            responseDTO.setMessage("Offers List Fetched SuccessFully");
            responseDTO.setSuccess(true);
            responseDTO.setData(jobOffersList);
        } else {
            responseDTO.setMessage("No Data Found");
            responseDTO.setSuccess(false);
        }
        return responseDTO;
    }


    public ResponseDTO getOffersByJobTitle(String jobTitle) {
        ResponseDTO responseDTO = new ResponseDTO();
         JobOffer jobOffer = jobOffersRepository.findByJobTitle(jobTitle);
        if (Objects.nonNull(jobOffer)){
            responseDTO = new ResponseDTO();
            responseDTO.setMessage("Offer found Successfully");
            responseDTO.setSuccess(true);
            responseDTO.setData(jobOffer);
        } else {
            responseDTO.setMessage("No offer Found");
            responseDTO.setSuccess(false);
        }
        return responseDTO;
    }



    public ResponseDTO getAllAplications() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<RecruiterResponseDTO> applicationList = jobApplicationRepository.getAllAplication();
        if (Objects.nonNull(applicationList) && applicationList.size()>0 ) {
            responseDTO = new ResponseDTO();
            responseDTO.setMessage("Application List Fetched SuccessFully");
            responseDTO.setSuccess(true);
            responseDTO.setData(applicationList);
        } else {
            responseDTO.setMessage("No Data Found");
            responseDTO.setSuccess(false);
        }
        return responseDTO;
    }

    public ResponseDTO getAllAplicationsByStatus(String status) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<RecruiterResponseDTO> applicationList = jobApplicationRepository.getAllAplication().stream().filter(it -> it.getApplicationStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
        if (Objects.nonNull(applicationList) && applicationList.size()>0) {
            responseDTO = new ResponseDTO();
            responseDTO.setMessage("Application List Fetched SuccessFully");
            responseDTO.setSuccess(true);
            responseDTO.setData(applicationList);
        } else {
            responseDTO.setMessage("No Data Found");
            responseDTO.setSuccess(false);
        }
        return responseDTO;
    }


    private ResponseDTO isJobTitleExist(String jobTitle) {
        ResponseDTO responseDTO = new ResponseDTO();
       if (jobOffersRepository.findByJobTitle(jobTitle) !=null) {
           responseDTO.setSuccess(true);
           responseDTO.setMessage("Job title already exist ");
       } else {
           responseDTO.setSuccess(false);
       }
       return  responseDTO;
    }

}
