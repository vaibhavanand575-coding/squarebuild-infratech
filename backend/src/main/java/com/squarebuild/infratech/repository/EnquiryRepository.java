package com.squarebuild.infratech.repository;

import com.squarebuild.infratech.entity.EnquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnquiryRepository extends JpaRepository<EnquiryEntity, Long> {
    List<EnquiryEntity> findAllByOrderByCreatedAtDesc();
}
