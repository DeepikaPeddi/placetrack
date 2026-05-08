package com.placetrack.placetrack.controller;

import com.placetrack.placetrack.model.JobApplication;
import com.placetrack.placetrack.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    @Autowired
    private JobApplicationService applicationService;

    // ✅ GET all applications → /api/applications
    @GetMapping
    public List<JobApplication> getAllApplications() {
        return applicationService.getAllApplications();
    }

    // ✅ GET application by ID → /api/applications/1
    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getApplicationById(@PathVariable Long id) {
        Optional<JobApplication> app = applicationService.getApplicationById(id);
        return app.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ GET all applications for a student → /api/applications/student/2
    @GetMapping("/student/{userId}")
    public List<JobApplication> getApplicationsByStudent(@PathVariable Long userId) {
        return applicationService.getApplicationsByStudent(userId);
    }

    // ✅ GET applications by status → /api/applications/status/SELECTED
    // Status values: APPLIED, INTERVIEW, SELECTED, REJECTED
    @GetMapping("/status/{status}")
    public List<JobApplication> getApplicationsByStatus(@PathVariable String status) {
        return applicationService.getApplicationsByStatus(status);
    }

    // ✅ GET applications for a student filtered by status
    // → /api/applications/student/2/status/INTERVIEW
    @GetMapping("/student/{userId}/status/{status}")
    public List<JobApplication> getByStudentAndStatus(@PathVariable Long userId,
                                                      @PathVariable String status) {
        return applicationService.getByUserIdAndStatus(userId, status);
    }

    // ✅ POST create application → /api/applications
    // Body: { "companyName": "Google", "role": "SDE", "status": "APPLIED", "user": { "id": 2 } }
    @PostMapping
    public ResponseEntity<JobApplication> createApplication(@RequestBody JobApplication application) {
        JobApplication saved = applicationService.saveApplication(application);
        return ResponseEntity.ok(saved);
    }

    // ✅ PUT update application (e.g., change status) → /api/applications/1
    @PutMapping("/{id}")
    public ResponseEntity<JobApplication> updateApplication(@PathVariable Long id,
                                                            @RequestBody JobApplication updated) {
        Optional<JobApplication> existing = applicationService.getApplicationById(id);
        if (existing.isPresent()) {
            updated.setId(id);
            return ResponseEntity.ok(applicationService.saveApplication(updated));
        }
        return ResponseEntity.notFound().build();
    }

    // ✅ DELETE application → /api/applications/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        if (applicationService.getApplicationById(id).isPresent()) {
            applicationService.deleteApplication(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
