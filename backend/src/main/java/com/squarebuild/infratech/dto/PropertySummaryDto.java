package com.squarebuild.infratech.dto;

import com.squarebuild.infratech.entity.PropertyStatus;
import com.squarebuild.infratech.entity.PropertyType;

import java.math.BigDecimal;

public class PropertySummaryDto {
    private Long id;
    private String slug;
    private String title;
    private PropertyType type;
    private BigDecimal price;
    private BigDecimal size;
    private String sizeUnit;
    private String location;
    private PropertyStatus status;
    private String statusLabel;
    private boolean featured;
    private String heroImage;

    public PropertySummaryDto(Long id, String slug, String title, PropertyType type, BigDecimal price,
                               BigDecimal size, String sizeUnit, String location, PropertyStatus status,
                               String statusLabel, boolean featured, String heroImage) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.type = type;
        this.price = price;
        this.size = size;
        this.sizeUnit = sizeUnit;
        this.location = location;
        this.status = status;
        this.statusLabel = statusLabel;
        this.featured = featured;
        this.heroImage = heroImage;
    }

    public Long getId() { return id; }
    public String getSlug() { return slug; }
    public String getTitle() { return title; }
    public PropertyType getType() { return type; }
    public BigDecimal getPrice() { return price; }
    public BigDecimal getSize() { return size; }
    public String getSizeUnit() { return sizeUnit; }
    public String getLocation() { return location; }
    public PropertyStatus getStatus() { return status; }
    public String getStatusLabel() { return statusLabel; }
    public boolean isFeatured() { return featured; }
    public String getHeroImage() { return heroImage; }
}
