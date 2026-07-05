package com.squarebuild.infratech.model;

import java.util.List;

public class Property extends PropertySummary {
    private final String description;
    private final double areaSqYards;
    private final int totalUnits;
    private final List<Feature> features;
    private final List<String> amenities;
    private final List<String> gallery;

    public Property(String slug, String name, PropertyType type, String location, String status,
                     double startingPrice, String priceUnit, String heroImage, String tagline,
                     String description, double areaSqYards, int totalUnits,
                     List<Feature> features, List<String> amenities, List<String> gallery) {
        super(slug, name, type, location, status, startingPrice, priceUnit, heroImage, tagline);
        this.description = description;
        this.areaSqYards = areaSqYards;
        this.totalUnits = totalUnits;
        this.features = features;
        this.amenities = amenities;
        this.gallery = gallery;
    }

    public String getDescription() { return description; }
    public double getAreaSqYards() { return areaSqYards; }
    public int getTotalUnits() { return totalUnits; }
    public List<Feature> getFeatures() { return features; }
    public List<String> getAmenities() { return amenities; }
    public List<String> getGallery() { return gallery; }
}
