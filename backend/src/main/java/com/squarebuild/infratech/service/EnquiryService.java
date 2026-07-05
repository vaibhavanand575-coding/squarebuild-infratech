package com.squarebuild.infratech.service;

import com.squarebuild.infratech.dto.EnquiryAdminDto;
import com.squarebuild.infratech.dto.EnquiryRequestDto;
import com.squarebuild.infratech.dto.EnquiryResponseDto;
import com.squarebuild.infratech.entity.EnquiryEntity;
import com.squarebuild.infratech.entity.PropertyEntity;
import com.squarebuild.infratech.repository.EnquiryRepository;
import com.squarebuild.infratech.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EnquiryService {

    private final EnquiryRepository enquiryRepository;
    private final PropertyRepository propertyRepository;
    private final MailService mailService;

    public EnquiryService(EnquiryRepository enquiryRepository, PropertyRepository propertyRepository, MailService mailService) {
        this.enquiryRepository = enquiryRepository;
        this.propertyRepository = propertyRepository;
        this.mailService = mailService;
    }

    @Transactional
    public EnquiryResponseDto submit(EnquiryRequestDto request) {
        EnquiryEntity entity = new EnquiryEntity();
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setMessage(request.getMessage());
        entity.setSource(request.getSource());

        if (request.getPropertySlug() != null && !request.getPropertySlug().isBlank()) {
            PropertyEntity property = propertyRepository.findBySlug(request.getPropertySlug()).orElse(null);
            entity.setProperty(property);
        }

        EnquiryEntity saved = enquiryRepository.save(entity);
        mailService.notifyNewEnquiry(saved);

        return new EnquiryResponseDto(
                saved.getId(),
                saved.getCreatedAt(),
                "Thanks " + saved.getName() + ", our team will reach out within 24 hours."
        );
    }

    public List<EnquiryAdminDto> findAll() {
        return enquiryRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(e -> new EnquiryAdminDto(
                        e.getId(), e.getName(), e.getEmail(), e.getPhone(), e.getMessage(),
                        e.getProperty() != null ? e.getProperty().getTitle() : null,
                        e.getSource(), e.getCreatedAt()))
                .toList();
    }
}
