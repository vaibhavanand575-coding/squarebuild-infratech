package com.squarebuild.infratech.service;

import com.squarebuild.infratech.model.CompanyStats;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    private final PropertyService propertyService;

    public StatsService(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    public CompanyStats getStats() {
        return new CompanyStats(73000, propertyService.count(), 480, 25, 2025);
    }
}
