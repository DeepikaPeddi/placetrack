package com.placetrack.placetrack.service;

import com.placetrack.placetrack.model.StudentProfile;
import com.placetrack.placetrack.model.User;
import com.placetrack.placetrack.repository.StudentProfileRepository;
import com.placetrack.placetrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentProfileService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private UserRepository userRepository;  // needed to validate user exists

    // ── CREATE ───────────────────────────────────────────
    public StudentProfile createProfile(Long userId, StudentProfile profile) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        profile.setUser(user);  // link profile to user
        return studentProfileRepository.save(profile);
    }

    // ── READ ALL ─────────────────────────────────────────
    public List<StudentProfile> getAllProfiles() {
        return studentProfileRepository.findAll();
    }

    // ── READ BY ID ───────────────────────────────────────
    public Optional<StudentProfile> getProfileById(Long id) {
        return studentProfileRepository.findById(id);
    }

    // ── READ BY USER ID ──────────────────────────────────
    public Optional<StudentProfile> getProfileByUserId(Long userId) {
        return studentProfileRepository.findByUser_Id(userId);
    }

    // ── UPDATE ───────────────────────────────────────────
    public StudentProfile updateProfile(Long id, StudentProfile updatedProfile) {
        return studentProfileRepository.findById(id).map(existing -> {
            existing.setCollege(updatedProfile.getCollege());
            existing.setBranch(updatedProfile.getBranch());
            existing.setCgpa(updatedProfile.getCgpa());
            existing.setSkills(updatedProfile.getSkills());
            existing.setResumeLink(updatedProfile.getResumeLink());
            existing.setPlacementStatus(updatedProfile.getPlacementStatus());
            return studentProfileRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));
    }

    // ── DELETE ───────────────────────────────────────────
    public void deleteProfile(Long id) {
        if (!studentProfileRepository.existsById(id)) {
            throw new RuntimeException("Profile not found with id: " + id);
        }
        studentProfileRepository.deleteById(id);
    }
}