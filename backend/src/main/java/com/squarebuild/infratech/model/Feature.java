package com.squarebuild.infratech.model;

public class Feature {
    private final String icon;
    private final String title;
    private final String description;

    public Feature(String icon, String title, String description) {
        this.icon = icon;
        this.title = title;
        this.description = description;
    }

    public String getIcon() { return icon; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
}
