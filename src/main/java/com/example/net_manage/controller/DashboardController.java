package com.example.net_manage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.net_manage.service.DashboardService;

// DashboardController (new class)
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired private DashboardService dashboardService;

    @GetMapping("/summary")
    public Map<String, Long> summary() {
        return dashboardService.summary();
    }
}
