package com.squarebuild.infratech.controller;

import com.squarebuild.infratech.dto.PropertyAdminRequestDto;
import com.squarebuild.infratech.dto.PropertyDetailDto;
import com.squarebuild.infratech.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/properties")
public class AdminPropertyController {

    private final PropertyService propertyService;

    public AdminPropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PropertyDetailDto create(@Valid @RequestBody PropertyAdminRequestDto request) {
        return propertyService.create(request);
    }

    @PutMapping("/{id}")
    public PropertyDetailDto update(@PathVariable Long id, @Valid @RequestBody PropertyAdminRequestDto request) {
        return propertyService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        propertyService.delete(id);
    }
}
