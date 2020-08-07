package com.quovantis.recruitment_demo.controller;

import com.quovantis.recruitment_demo.common.ApiConstants;
import com.quovantis.recruitment_demo.common.response.ResponseDTO;
import com.quovantis.recruitment_demo.common.response.ResponseHandler;
import com.quovantis.recruitment_demo.dto.ApplicantRequestDTO;
import com.quovantis.recruitment_demo.services.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@RestController
@RequestMapping(ApiConstants.APPLICANT)
public class ApplicantController {

    @Autowired
    ApplicantService applicantService;

    /*Apply for a JOb  Offer*/

    @PostMapping("/apply")
    public ResponseEntity <ResponseDTO> applyForJob (@Valid @RequestBody ApplicantRequestDTO applicantRequestDTO) {
       ResponseDTO responseDTO =  applicantService.applyForJob(applicantRequestDTO);
       if(responseDTO.isSuccess()) {
           return ResponseHandler.generateResponse(HttpStatus.OK,true,responseDTO,"Success");
       } else {
           return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR,false,responseDTO,"Internal Server Error");
       }
    }

    /*Applicant  can look  for job he applied  */

    @GetMapping("/applied-jobs/email/{email}")
    public ResponseEntity <ResponseDTO> jobsAppliedByApplicant(@PathVariable("email") @NotBlank @Size(min = 10)String email) {
        ResponseDTO responseDTO =  applicantService.jobAppliedByApplicant(email);
        if(responseDTO.isSuccess()) {
            return ResponseHandler.generateResponse(HttpStatus.OK,true,responseDTO,"Success");
        } else {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR,false,responseDTO,"Internal Server Error");
        }
    }

}
