package com.squarebuild.infratech.service;

import com.squarebuild.infratech.model.Feature;
import com.squarebuild.infratech.model.Property;
import com.squarebuild.infratech.model.PropertySummary;
import com.squarebuild.infratech.model.PropertyType;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PropertyService {

    private final Map<String, Property> properties = new LinkedHashMap<>();

    public PropertyService() {
        seed("urban-paradise-city",
                "The Urban Paradise City", PropertyType.PLOT, "Aligarh–Palwal Highway, near Jewar",
                "New Launch", 19800, "per Sq. Yard",
                "https://picsum.photos/seed/urban-paradise-hero/1600/900",
                "Plots & villas in a gated township minutes from Jewar International Airport",
                "Invest in a premium plotted township near the upcoming Jewar International Airport, "
                        + "offering excellent connectivity and high future growth. Enjoy a secure gated community "
                        + "with modern infrastructure, wide roads, and a lifestyle designed for comfort and long-term value.",
                32000, 480,
                List.of(
                        new Feature("map-pin", "Prime Location", "Strategically located on Aligarh–Palwal Highway, close to major development zones."),
                        new Feature("shield", "Gated & Secure", "24/7 security with a well-planned gated township for peaceful living."),
                        new Feature("layout", "Modern Infrastructure", "Wide internal roads, proper drainage, street lights, and strong development planning."),
                        new Feature("trending-up", "High Appreciation", "Ideal for smart investors due to upcoming airport and fast growth in the area.")
                ),
                List.of("24/7 Security & Gated Community", "Wide Internal Roads", "Landscaped Parks & Green Area",
                        "Community Center", "Street Lights & Underground Services", "Water Supply & Proper Drainage",
                        "Electricity Connection", "Kids Play Area"),
                List.of("urban-paradise-1", "urban-paradise-2", "urban-paradise-3", "urban-paradise-4")
        );

        seed("green-meadows-farms",
                "Green Meadows Farms", PropertyType.FARMHOUSE, "Sohna Road, Gurugram",
                "Ready to Move", 45000, "per Sq. Yard",
                "https://picsum.photos/seed/green-meadows-hero/1600/900",
                "Weekend farmhouses set on landscaped acreage an hour from the city",
                "A curated collection of farmhouse plots for buyers who want space, greenery, and a quiet retreat "
                        + "without leaving easy reach of the city. Each plot backs onto shared orchards and a central clubhouse.",
                18000, 25,
                List.of(
                        new Feature("map-pin", "Easy Access", "45 minutes from central Gurugram via NH48 and Sohna Road."),
                        new Feature("shield", "Private & Gated", "Boundary walls and a single manned entry point for every cluster."),
                        new Feature("layout", "Farm Infrastructure", "Drip irrigation lines, borewells, and shared orchard maintenance."),
                        new Feature("trending-up", "Weekend Living", "Built for second-home buyers seeking a nearby countryside escape.")
                ),
                List.of("Clubhouse & Pool", "Shared Orchards", "Borewell & Irrigation", "24/7 Security",
                        "Landscaped Common Areas", "Power Backup", "Rainwater Harvesting"),
                List.of("green-meadows-1", "green-meadows-2", "green-meadows-3")
        );

        seed("royal-enclave-villas",
                "Royal Enclave Villas", PropertyType.VILLA, "Dwarka Expressway, Gurugram",
                "Under Construction", 1.85e7, "per Villa",
                "https://picsum.photos/seed/royal-enclave-hero/1600/900",
                "Premium 4BHK villas with private gardens on the Dwarka Expressway corridor",
                "Independent villas designed for large families, with double-height living rooms, private gardens, "
                        + "and a shared clubhouse. Built to premium specifications with branded fittings throughout.",
                9500, 60,
                List.of(
                        new Feature("map-pin", "Expressway Access", "Direct access to Dwarka Expressway and NH48."),
                        new Feature("shield", "Gated Community", "Single entry/exit with round-the-clock security staff."),
                        new Feature("layout", "Premium Build", "Branded fittings, double-height ceilings, private gardens."),
                        new Feature("trending-up", "Strong Rental Demand", "Close to upcoming commercial hubs and IT corridors.")
                ),
                List.of("Private Garden per Villa", "Clubhouse with Pool", "Gymnasium", "24/7 Security & CCTV",
                        "Covered Car Parking", "Power Backup", "Landscaped Podium Garden"),
                List.of("royal-enclave-1", "royal-enclave-2", "royal-enclave-3", "royal-enclave-4")
        );

        seed("business-square-retail",
                "Business Square", PropertyType.COMMERCIAL, "Yamuna Expressway, near Jewar",
                "New Launch", 65000, "per Sq. Ft.",
                "https://picsum.photos/seed/business-square-hero/1600/900",
                "Retail and office spaces positioned for the footfall around the new airport",
                "A mixed-use commercial development combining retail shopfronts and office floors, positioned to "
                        + "capture footfall from the residential townships and traffic building up around Jewar airport.",
                6000, 120,
                List.of(
                        new Feature("map-pin", "Airport-Facing", "Direct frontage on the Yamuna Expressway service road."),
                        new Feature("shield", "Managed Facility", "Dedicated facility management and common-area upkeep."),
                        new Feature("layout", "Flexible Floor Plates", "Configurable retail and office units from 300–5,000 sq. ft."),
                        new Feature("trending-up", "Early-Mover Pricing", "Pre-launch rates ahead of airport-driven demand.")
                ),
                List.of("Food Court", "Multi-level Parking", "Power Backup", "Central Air Conditioning",
                        "High-speed Elevators", "24/7 Security", "Fire Safety Systems"),
                List.of("business-square-1", "business-square-2", "business-square-3")
        );
    }

    private void seed(String slug, String name, PropertyType type, String location, String status,
                       double startingPrice, String priceUnit, String heroImage, String tagline,
                       String description, double areaSqYards, int totalUnits,
                       List<Feature> features, List<String> amenities, List<String> gallerySeeds) {
        List<String> gallery = gallerySeeds.stream()
                .map(seed -> "https://picsum.photos/seed/" + seed + "/1000/750")
                .toList();
        properties.put(slug, new Property(slug, name, type, location, status, startingPrice, priceUnit,
                heroImage, tagline, description, areaSqYards, totalUnits, features, amenities, gallery));
    }

    public List<PropertySummary> findAllSummaries() {
        return properties.values().stream()
                .map(p -> new PropertySummary(p.getSlug(), p.getName(), p.getType(), p.getLocation(), p.getStatus(),
                        p.getStartingPrice(), p.getPriceUnit(), p.getHeroImage(), p.getTagline()))
                .toList();
    }

    public Optional<Property> findBySlug(String slug) {
        return Optional.ofNullable(properties.get(slug));
    }

    public int count() {
        return properties.size();
    }
}
