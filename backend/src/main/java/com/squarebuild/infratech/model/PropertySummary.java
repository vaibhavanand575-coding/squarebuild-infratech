package com.squarebuild.infratech.model;

public class PropertySummary {
    private final String slug;
    private final String name;
    private final PropertyType type;
    private final String location;
    private final String status;
    private final double startingPrice;
    private final String priceUnit;
    private final String heroImage;
    private final String tagline;

    public PropertySummary(String slug, String name, PropertyType type, String location, String status,
                            double startingPrice, String priceUnit, String heroImage, String tagline) {
        this.slug = slug;
        this.name = name;
        this.type = type;
        this.location = location;
        this.status = status;
        this.startingPrice = startingPrice;
        this.priceUnit = priceUnit;
        this.heroImage = heroImage;
        this.tagline = tagline;
    }

    public String getSlug() { return slug; }
    public String getName() { return name; }
    public PropertyType getType() { return type; }
    public String getLocation() { return location; }
    public String getStatus() { return status; }
    public double getStartingPrice() { return startingPrice; }
    public String getPriceUnit() { return priceUnit; }
    public String getHeroImage() { return heroImage; }
    public String getTagline() { return tagline; }
}
