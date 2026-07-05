package com.squarebuild.infratech.controller;

import com.squarebuild.infratech.model.Testimonial;
import com.squarebuild.infratech.service.TestimonialService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/testimonials")
public class TestimonialController {

    private final TestimonialService testimonialService;

    public TestimonialController(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
    }

    @GetMapping
    public List<Testimonial> getAll() {
        return testimonialService.findAll();
    }
}
