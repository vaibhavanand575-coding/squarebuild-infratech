package com.squarebuild.infratech.dto;

import com.squarebuild.infratech.entity.PropertyStatus;
import com.squarebuild.infratech.entity.PropertyType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class PropertyDetailDto {
    private Long id;
    private String slug;
    private String title;
    private PropertyType type;
    private String description;
    private BigDecimal price;
    private BigDecimal size;
    private String sizeUnit;
    private Integer totalUnits;
    private String location;
    private PropertyStatus status;
    private String statusLabel;
    private boolean featured;
    private String heroImage;
    private List<String> imageUrls;
    private List<String> amenities;
    private Instant createdAt;

    public PropertyDetailDto(Long id, String slug, String title, PropertyType type, String description,
                              BigDecimal price, BigDecimal size, String sizeUnit, Integer totalUnits,
                              String location, PropertyStatus status, String statusLabel, boolean featured,
                              String heroImage, List<String> imageUrls, List<String> amenities, Instant createdAt) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.type = type;
        this.description = description;
        this.price = price;
        this.size = size;
        this.sizeUnit = sizeUnit;
        this.totalUnits = totalUnits;
        this.location = location;
        this.status = status;
        this.statusLabel = statusLabel;
        this.featured = featured;
        this.heroImage = heroImage;
        this.imageUrls = imageUrls;
        this.amenities = amenities;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getSlug() { return slug; }
    public String getTitle() { return title; }
    public PropertyType getType() { return type; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
    public BigDecimal getSize() { return size; }
    public String getSizeUnit() { return sizeUnit; }
    public Integer getTotalUnits() { return totalUnits; }
    public String getLocation() { return location; }
    public PropertyStatus getStatus() { return status; }
    public String getStatusLabel() { return statusLabel; }
    public boolean isFeatured() { return featured; }
    public String getHeroImage() { return heroImage; }
    public List<String> getImageUrls() { return imageUrls; }
    public List<String> getAmenities() { return amenities; }
    public Instant getCreatedAt() { return createdAt; }
}
