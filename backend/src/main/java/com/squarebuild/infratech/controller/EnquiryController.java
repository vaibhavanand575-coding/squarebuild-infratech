package com.squarebuild.infratech.controller;

import com.squarebuild.infratech.dto.EnquiryRequestDto;
import com.squarebuild.infratech.dto.EnquiryResponseDto;
import com.squarebuild.infratech.service.EnquiryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enquiries")
public class EnquiryController {

    private final EnquiryService enquiryService;

    public EnquiryController(EnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnquiryResponseDto submit(@Valid @RequestBody EnquiryRequestDto request) {
        return enquiryService.submit(request);
    }
}
