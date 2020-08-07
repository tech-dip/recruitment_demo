package com.quovantis.recruitment_demo.controller;

import com.quovantis.recruitment_demo.common.ApiConstants;
import com.quovantis.recruitment_demo.common.response.ResponseDTO;
import com.quovantis.recruitment_demo.common.response.ResponseHandler;
import com.quovantis.recruitment_demo.dto.ApplicantRequestDTO;
import com.quovantis.recruitment_demo.dto.RecruiterRequestDTO;
import com.quovantis.recruitment_demo.services.ApplicantService;
import com.quovantis.recruitment_demo.services.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(ApiConstants.RECRUITMENT)
public class RecruiterController {

    @Autowired
    RecruiterService recruiterService;
    @Autowired
    ApplicantService applicantService;

    /*Create or Update Offers*/
    @PostMapping("/create-or-update-offer")
    public ResponseEntity<?> createOrUpdateOffer(@Valid @RequestBody RecruiterRequestDTO recruiterRequestDTO) {
        ResponseDTO responseDTO = recruiterService.createOrUpdateJobOffer(recruiterRequestDTO);
        if (responseDTO.isSuccess()) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true,responseDTO , "Success");
        } else {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, false, responseDTO, "Internal Server Error");
        }
    }

    /*List all Offer*/
    @GetMapping("/list-offer")
    public ResponseEntity<ResponseDTO> getAllOffer() {
        ResponseDTO responseDTO = recruiterService.getOffersList();
        if (responseDTO.isSuccess()) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, responseDTO, "Success");
        } else {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, false, responseDTO, "Internal Server error");
        }
    }


    /*List single Offer by job title*/
    @GetMapping("/list-offer/job-title/{jobTitle}")
    public ResponseEntity<ResponseDTO> getOfferByJobTitle(@PathVariable ("jobTitle") @NotBlank String jobTittle) {
        ResponseDTO responseDTO = recruiterService.getOffersByJobTitle(jobTittle);
        if (responseDTO.isSuccess()) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, responseDTO, "Success");
        } else {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, false, responseDTO, "Internal Server error");
        }
    }


    /*List all Application  applied*/
    @GetMapping("/list-applications")
    public ResponseEntity<ResponseDTO> getAllApplication() {
        ResponseDTO responseDTO = recruiterService.getAllAplications();
        if (responseDTO.isSuccess()) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, responseDTO, "Success");
        } else {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, false, responseDTO, "Internal Server Error");
        }
    }

    /*List all Application  applied by status*/
    @GetMapping("/list-applications/status/{status}")
    public ResponseEntity<ResponseDTO> getAllApplicationByStatus(@PathVariable ("status")  @NotBlank String status) {
        ResponseDTO responseDTO= recruiterService.getAllAplicationsByStatus(status);
        if (responseDTO.isSuccess()) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, responseDTO, "Success");
        } else {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, false, responseDTO, "Internal Server Error");
        }
    }

    /* Recruiter can update Application Status as INVITED, REJECTED, HIRED */
    @PostMapping("/update-application-status")
    public ResponseEntity<?> UpdateApplicationStatus( @RequestBody ApplicantRequestDTO applicantRequestDTO) {
        ResponseDTO responseDTO = applicantService.updateApplicationStatus(applicantRequestDTO);
        if (responseDTO.isSuccess()) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true,responseDTO , "Success");
        } else {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, false, responseDTO, "Internal server error");
        }
    }


}
