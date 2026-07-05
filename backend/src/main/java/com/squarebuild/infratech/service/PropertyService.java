package com.squarebuild.infratech.service;

import com.squarebuild.infratech.dto.PropertyAdminRequestDto;
import com.squarebuild.infratech.dto.PropertyDetailDto;
import com.squarebuild.infratech.dto.PropertySummaryDto;
import com.squarebuild.infratech.entity.PropertyEntity;
import com.squarebuild.infratech.entity.PropertyStatus;
import com.squarebuild.infratech.entity.PropertyType;
import com.squarebuild.infratech.repository.PropertyRepository;
import com.squarebuild.infratech.repository.PropertySpecifications;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<PropertySummaryDto> findAll(PropertyType type, PropertyStatus status, BigDecimal minPrice, BigDecimal maxPrice) {
        var spec = PropertySpecifications.withFilters(type, status, minPrice, maxPrice);
        return propertyRepository.findAll(spec).stream().map(this::toSummaryDto).toList();
    }

    public PropertyDetailDto findBySlug(String slug) {
        PropertyEntity entity = propertyRepository.findBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));
        return toDetailDto(entity);
    }

    public Optional<PropertyEntity> findEntityBySlug(String slug) {
        return propertyRepository.findBySlug(slug);
    }

    public long count() {
        return propertyRepository.count();
    }

    @Transactional
    public PropertyDetailDto create(PropertyAdminRequestDto request) {
        if (propertyRepository.existsBySlug(request.getSlug())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A property with this slug already exists");
        }
        PropertyEntity entity = new PropertyEntity();
        applyRequest(entity, request);
        return toDetailDto(propertyRepository.save(entity));
    }

    @Transactional
    public PropertyDetailDto update(Long id, PropertyAdminRequestDto request) {
        PropertyEntity entity = propertyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));
        applyRequest(entity, request);
        return toDetailDto(propertyRepository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        if (!propertyRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found");
        }
        propertyRepository.deleteById(id);
    }

    private void applyRequest(PropertyEntity entity, PropertyAdminRequestDto request) {
        entity.setSlug(request.getSlug());
        entity.setTitle(request.getTitle());
        entity.setType(request.getType());
        entity.setDescription(request.getDescription());
        entity.setPrice(request.getPrice());
        entity.setSize(request.getSize());
        entity.setSizeUnit(request.getSizeUnit());
        entity.setTotalUnits(request.getTotalUnits());
        entity.setLocation(request.getLocation());
        entity.setStatus(request.getStatus());
        entity.setStatusLabel(request.getStatusLabel());
        entity.setFeatured(request.isFeatured());
        entity.setHeroImage(request.getHeroImage());
        entity.setImageUrls(request.getImageUrls() != null ? request.getImageUrls() : List.of());
        entity.setAmenities(request.getAmenities() != null ? request.getAmenities() : List.of());
    }

    private PropertySummaryDto toSummaryDto(PropertyEntity e) {
        return new PropertySummaryDto(e.getId(), e.getSlug(), e.getTitle(), e.getType(), e.getPrice(),
                e.getSize(), e.getSizeUnit(), e.getLocation(), e.getStatus(), e.getStatusLabel(),
                e.isFeatured(), e.getHeroImage());
    }

    private PropertyDetailDto toDetailDto(PropertyEntity e) {
        return new PropertyDetailDto(e.getId(), e.getSlug(), e.getTitle(), e.getType(), e.getDescription(),
                e.getPrice(), e.getSize(), e.getSizeUnit(), e.getTotalUnits(), e.getLocation(), e.getStatus(),
                e.getStatusLabel(), e.isFeatured(), e.getHeroImage(), e.getImageUrls(), e.getAmenities(),
                e.getCreatedAt());
    }
}
