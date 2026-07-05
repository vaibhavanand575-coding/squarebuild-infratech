package com.squarebuild.infratech.repository;

import com.squarebuild.infratech.entity.PropertyEntity;
import com.squarebuild.infratech.entity.PropertyStatus;
import com.squarebuild.infratech.entity.PropertyType;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public final class PropertySpecifications {

    private PropertySpecifications() {}

    public static Specification<PropertyEntity> withFilters(
            PropertyType type, PropertyStatus status,
            BigDecimal minPrice, BigDecimal maxPrice,
            BigDecimal minSize, BigDecimal maxSize) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();
            if (type != null) {
                predicates = cb.and(predicates, cb.equal(root.get("type"), type));
            }
            if (status != null) {
                predicates = cb.and(predicates, cb.equal(root.get("status"), status));
            }
            if (minPrice != null) {
                predicates = cb.and(predicates, cb.ge(root.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicates = cb.and(predicates, cb.le(root.get("price"), maxPrice));
            }
            if (minSize != null) {
                predicates = cb.and(predicates, cb.ge(root.get("size"), minSize));
            }
            if (maxSize != null) {
                predicates = cb.and(predicates, cb.le(root.get("size"), maxSize));
            }
            return predicates;
        };
    }
}
