package com.squarebuild.infratech.controller;

import com.squarebuild.infratech.model.GalleryImage;
import com.squarebuild.infratech.service.GalleryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/gallery")
public class GalleryController {

    private final GalleryService galleryService;

    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping
    public List<GalleryImage> getAll() {
        return galleryService.findAll();
    }
}
