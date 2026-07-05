package com.squarebuild.infratech.model;

public class GalleryImage {
    private final String url;
    private final String caption;
    private final String category;

    public GalleryImage(String url, String caption, String category) {
        this.url = url;
        this.caption = caption;
        this.category = category;
    }

    public String getUrl() { return url; }
    public String getCaption() { return caption; }
    public String getCategory() { return category; }
}
