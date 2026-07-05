package com.squarebuild.infratech.seed;

import com.squarebuild.infratech.entity.PropertyEntity;
import com.squarebuild.infratech.entity.PropertyStatus;
import com.squarebuild.infratech.entity.PropertyType;
import com.squarebuild.infratech.repository.PropertyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Seeds a few sample properties on first startup so the frontend has
 * real-looking data. Runs only when the properties table is empty, so
 * restarts never duplicate rows.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final PropertyRepository propertyRepository;

    public DataSeeder(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public void run(String... args) {
        if (propertyRepository.count() > 0) {
            return;
        }

        propertyRepository.saveAll(List.of(
                property("urban-paradise-city", "The Urban Paradise City", PropertyType.PLOT,
                        "Invest in a premium plotted township near the upcoming Jewar International Airport, "
                                + "offering excellent connectivity and high future growth. Enjoy a secure gated "
                                + "community with modern infrastructure, wide roads, and a lifestyle designed for "
                                + "comfort and long-term value.",
                        new BigDecimal("1980000"), new BigDecimal("100"), "Sq. Yards", 480,
                        "Aligarh–Palwal Highway, near Jewar", PropertyStatus.AVAILABLE, "New Launch", true,
                        "https://picsum.photos/seed/urban-paradise-hero/1600/900",
                        List.of("https://picsum.photos/seed/urban-paradise-1/1000/750",
                                "https://picsum.photos/seed/urban-paradise-2/1000/750",
                                "https://picsum.photos/seed/urban-paradise-3/1000/750",
                                "https://picsum.photos/seed/urban-paradise-4/1000/750"),
                        List.of("24/7 Security & Gated Community", "Wide Internal Roads", "Landscaped Parks & Green Area",
                                "Community Center", "Street Lights & Underground Services", "Water Supply & Proper Drainage",
                                "Electricity Connection", "Kids Play Area")),

                property("green-meadows-farms", "Green Meadows Farms", PropertyType.FARMHOUSE,
                        "A curated collection of farmhouse plots for buyers who want space, greenery, and a quiet "
                                + "retreat without leaving easy reach of the city. Each plot backs onto shared "
                                + "orchards and a central clubhouse.",
                        new BigDecimal("8100000"), new BigDecimal("180"), "Sq. Yards", 25,
                        "Sohna Road, Gurugram", PropertyStatus.AVAILABLE, "Ready to Move", true,
                        "https://picsum.photos/seed/green-meadows-hero/1600/900",
                        List.of("https://picsum.photos/seed/green-meadows-1/1000/750",
                                "https://picsum.photos/seed/green-meadows-2/1000/750",
                                "https://picsum.photos/seed/green-meadows-3/1000/750"),
                        List.of("Clubhouse & Pool", "Shared Orchards", "Borewell & Irrigation", "24/7 Security",
                                "Landscaped Common Areas", "Power Backup", "Rainwater Harvesting")),

                property("royal-enclave-villas", "Royal Enclave Villas", PropertyType.VILLA,
                        "Independent villas designed for large families, with double-height living rooms, private "
                                + "gardens, and a shared clubhouse. Built to premium specifications with branded "
                                + "fittings throughout.",
                        new BigDecimal("18500000"), new BigDecimal("350"), "Sq. Yards", 60,
                        "Dwarka Expressway, Gurugram", PropertyStatus.AVAILABLE, "Under Construction", false,
                        "https://picsum.photos/seed/royal-enclave-hero/1600/900",
                        List.of("https://picsum.photos/seed/royal-enclave-1/1000/750",
                                "https://picsum.photos/seed/royal-enclave-2/1000/750",
                                "https://picsum.photos/seed/royal-enclave-3/1000/750",
                                "https://picsum.photos/seed/royal-enclave-4/1000/750"),
                        List.of("Private Garden per Villa", "Clubhouse with Pool", "Gymnasium", "24/7 Security & CCTV",
                                "Covered Car Parking", "Power Backup", "Landscaped Podium Garden"))
        ));
    }

    private PropertyEntity property(String slug, String title, PropertyType type, String description,
                                     BigDecimal price, BigDecimal size, String sizeUnit, int totalUnits,
                                     String location, PropertyStatus status, String statusLabel, boolean featured,
                                     String heroImage, List<String> imageUrls, List<String> amenities) {
        PropertyEntity e = new PropertyEntity();
        e.setSlug(slug);
        e.setTitle(title);
        e.setType(type);
        e.setDescription(description);
        e.setPrice(price);
        e.setSize(size);
        e.setSizeUnit(sizeUnit);
        e.setTotalUnits(totalUnits);
        e.setLocation(location);
        e.setStatus(status);
        e.setStatusLabel(statusLabel);
        e.setFeatured(featured);
        e.setHeroImage(heroImage);
        e.setImageUrls(imageUrls);
        e.setAmenities(amenities);
        return e;
    }
}
