package com.squarebuild.infratech.service;

import com.squarebuild.infratech.model.Testimonial;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestimonialService {

    private final List<Testimonial> testimonials = List.of(
            new Testimonial("Rohit Sharma", "Aligarh", "The site visit team was transparent about the timelines and paperwork. Booked a corner plot in Urban Paradise City and the process was smooth end to end.", 5),
            new Testimonial("Neha Kapoor", "Delhi", "We wanted a weekend farmhouse close enough to Gurugram for regular visits. Green Meadows delivered exactly that, and the clubhouse is a nice bonus.", 5),
            new Testimonial("Anil Verma", "Gurugram", "Royal Enclave's construction quality stood out compared to other villa projects we shortlisted. Regular updates were shared throughout construction.", 4),
            new Testimonial("Priya Nair", "Noida", "Booked a retail unit in Business Square as an investment. The team's projections around airport-driven footfall matched what we'd researched ourselves.", 5)
    );

    public List<Testimonial> findAll() {
        return testimonials;
    }
}
