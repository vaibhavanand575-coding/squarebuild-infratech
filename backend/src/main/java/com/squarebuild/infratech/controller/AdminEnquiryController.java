package com.squarebuild.infratech.controller;

import com.squarebuild.infratech.dto.EnquiryAdminDto;
import com.squarebuild.infratech.service.EnquiryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/enquiries")
public class AdminEnquiryController {

    private final EnquiryService enquiryService;

    public AdminEnquiryController(EnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }

    @GetMapping
    public List<EnquiryAdminDto> getAll() {
        return enquiryService.findAll();
    }
}
