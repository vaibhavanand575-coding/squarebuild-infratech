package com.squarebuild.infratech.controller;

import com.squarebuild.infratech.model.Property;
import com.squarebuild.infratech.model.PropertySummary;
import com.squarebuild.infratech.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public List<PropertySummary> getAll() {
        return propertyService.findAllSummaries();
    }

    @GetMapping("/{slug}")
    public Property getOne(@PathVariable String slug) {
        return propertyService.findBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));
    }
}
