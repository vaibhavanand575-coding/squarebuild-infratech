package com.squarebuild.infratech.service;

import com.squarebuild.infratech.model.GalleryImage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryService {

    private final List<GalleryImage> images = List.of(
            new GalleryImage("https://picsum.photos/seed/gallery-exterior-1/900/700", "Township entrance gate", "EXTERIOR"),
            new GalleryImage("https://picsum.photos/seed/gallery-exterior-2/900/700", "Landscaped park area", "EXTERIOR"),
            new GalleryImage("https://picsum.photos/seed/gallery-exterior-3/900/700", "Villa street view", "EXTERIOR"),
            new GalleryImage("https://picsum.photos/seed/gallery-interior-1/900/700", "Model home living room", "INTERIOR"),
            new GalleryImage("https://picsum.photos/seed/gallery-interior-2/900/700", "Model home kitchen", "INTERIOR"),
            new GalleryImage("https://picsum.photos/seed/gallery-interior-3/900/700", "Master bedroom", "INTERIOR"),
            new GalleryImage("https://picsum.photos/seed/gallery-amenity-1/900/700", "Clubhouse pool", "AMENITY"),
            new GalleryImage("https://picsum.photos/seed/gallery-amenity-2/900/700", "Kids play area", "AMENITY"),
            new GalleryImage("https://picsum.photos/seed/gallery-amenity-3/900/700", "Community center", "AMENITY"),
            new GalleryImage("https://picsum.photos/seed/gallery-site-1/900/700", "Aerial site progress", "SITE"),
            new GalleryImage("https://picsum.photos/seed/gallery-site-2/900/700", "Road construction", "SITE"),
            new GalleryImage("https://picsum.photos/seed/gallery-site-3/900/700", "Site office", "SITE")
    );

    public List<GalleryImage> findAll() {
        return images;
    }
}
