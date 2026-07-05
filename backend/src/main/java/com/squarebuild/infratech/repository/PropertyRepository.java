package com.squarebuild.infratech.repository;

import com.squarebuild.infratech.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<PropertyEntity, Long>, JpaSpecificationExecutor<PropertyEntity> {
    Optional<PropertyEntity> findBySlug(String slug);
    boolean existsBySlug(String slug);
}
