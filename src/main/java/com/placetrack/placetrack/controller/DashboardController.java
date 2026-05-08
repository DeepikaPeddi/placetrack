package com.placetrack.placetrack.controller;

import com.placetrack.placetrack.dto.UserDashboardDTO;
import com.placetrack.placetrack.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/user/{userId}")
    public UserDashboardDTO getDashboard(
            @PathVariable Long userId) {

        return dashboardService.getDashboard(userId);
    }
}
