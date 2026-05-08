package com.placetrack.placetrack.service;

import com.placetrack.placetrack.model.JobApplication;
import com.placetrack.placetrack.model.StudentProfile;
import com.placetrack.placetrack.repository.JobApplicationRepository;
import com.placetrack.placetrack.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository; // validate student exists

    // ── CREATE ───────────────────────────────────────────
    public JobApplication applyToJob(Long studentProfileId, JobApplication application) {
        StudentProfile profile = studentProfileRepository.findById(studentProfileId)
                .orElseThrow(() -> new RuntimeException("Student profile not found with id: " + studentProfileId));

        application.setUser(profile.getUser());  // link application → student
        application.setStatus(JobApplication.Status.APPLIED);        // default status on creation
        return jobApplicationRepository.save(application);
    }

    // ── READ ALL ─────────────────────────────────────────
    public List<JobApplication> getAllApplications() {
        return jobApplicationRepository.findAll();
    }

    // ── READ BY ID ───────────────────────────────────────
    public Optional<JobApplication> getApplicationById(Long id) {
        return jobApplicationRepository.findById(id);
    }

    // ── READ BY STUDENT ──────────────────────────────────
    public List<JobApplication> getApplicationsByStudent(Long studentProfileId) {
        return jobApplicationRepository.findByUser_Id(studentProfileId);
        //return jobApplicationRepository.findByStudentProfileId(studentProfileId);
    }

    // ── READ BY STATUS ───────────────────────────────────
    public List<JobApplication> getApplicationsByStatus(String status) {
        return jobApplicationRepository.findByStatus(
                JobApplication.Status.valueOf(status)
        );
        //return jobApplicationRepository.findByStatus(status);
    }
    // ── READ BY USER + STATUS ─────────────────────────
    public List<JobApplication> getByUserIdAndStatus(Long userId, String status) {

        List<JobApplication> applications =
                jobApplicationRepository.findByUser_Id(userId);

        JobApplication.Status enumStatus =
                JobApplication.Status.valueOf(status);

        return applications.stream()
                .filter(app -> app.getStatus() == enumStatus)
                .toList();
    }

    // ── UPDATE STATUS ONLY ───────────────────────────────
    public JobApplication updateStatus(Long id, String newStatus) {
        return jobApplicationRepository.findById(id).map(existing -> {
            existing.setStatus(JobApplication.Status.valueOf(newStatus)); // e.g. APPLIED → INTERVIEW → OFFERED → REJECTED
            return jobApplicationRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Application not found with id: " + id));
    }

    // ── UPDATE FULL ──────────────────────────────────────
    public JobApplication updateApplication(Long id, JobApplication updated) {
        return jobApplicationRepository.findById(id).map(existing -> {
            existing.setCompanyName(updated.getCompanyName());
            existing.setRole(updated.getRole());
            existing.setAppliedDate(updated.getAppliedDate());
            existing.setStatus(updated.getStatus());
            return jobApplicationRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Application not found with id: " + id));
    }
    // ── SAVE APPLICATION ─────────────────────────────
    public JobApplication saveApplication(JobApplication application) {

        if (application.getStatus() == null) {
            application.setStatus(JobApplication.Status.APPLIED);
        }

        return jobApplicationRepository.save(application);
    }

    // ── DELETE ───────────────────────────────────────────
    public void deleteApplication(Long id) {
        if (!jobApplicationRepository.existsById(id)) {
            throw new RuntimeException("Application not found with id: " + id);
        }
        jobApplicationRepository.deleteById(id);
    }
}
