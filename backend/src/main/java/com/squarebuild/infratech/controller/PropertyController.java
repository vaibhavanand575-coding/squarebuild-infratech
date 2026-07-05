package com.squarebuild.infratech.controller;

import com.squarebuild.infratech.dto.PropertyDetailDto;
import com.squarebuild.infratech.dto.PropertySummaryDto;
import com.squarebuild.infratech.entity.PropertyStatus;
import com.squarebuild.infratech.entity.PropertyType;
import com.squarebuild.infratech.service.PropertyService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public List<PropertySummaryDto> getAll(
            @RequestParam(required = false) PropertyType type,
            @RequestParam(required = false) PropertyStatus status,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) BigDecimal minSize,
            @RequestParam(required = false) BigDecimal maxSize
    ) {
        return propertyService.findAll(type, status, minPrice, maxPrice, minSize, maxSize);
    }

    @GetMapping("/{slug}")
    public PropertyDetailDto getOne(@PathVariable String slug) {
        return propertyService.findBySlug(slug);
    }
}
