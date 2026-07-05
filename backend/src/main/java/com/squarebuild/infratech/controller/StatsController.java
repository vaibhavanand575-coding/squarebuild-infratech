package com.squarebuild.infratech.controller;

import com.squarebuild.infratech.model.CompanyStats;
import com.squarebuild.infratech.service.StatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping
    public CompanyStats getStats() {
        return statsService.getStats();
    }
}
