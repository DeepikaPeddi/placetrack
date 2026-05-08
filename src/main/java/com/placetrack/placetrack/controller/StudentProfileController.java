package com.placetrack.placetrack.controller;

import com.placetrack.placetrack.model.StudentProfile;
import com.placetrack.placetrack.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profiles")
public class StudentProfileController {

    @Autowired
    private StudentProfileService profileService;

    // ✅ GET all profiles → /api/profiles
    @GetMapping
    public List<StudentProfile> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    // ✅ GET profile by profile ID → /api/profiles/1
    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getProfileById(@PathVariable Long id) {
        Optional<StudentProfile> profile = profileService.getProfileById(id);
        return profile.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ GET profile by User ID → /api/profiles/user/3
    // Useful when frontend knows the logged-in userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<StudentProfile> getProfileByUserId(@PathVariable Long userId) {
        Optional<StudentProfile> profile = profileService.getProfileByUserId(userId);
        return profile.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ POST create profile → /api/profiles
    // Body: { "branch": "CSE", "cgpa": 8.5, "skills": "Java, SQL", "user": { "id": 1 } }
    @PostMapping
    public ResponseEntity<StudentProfile> createProfile(
            @RequestBody StudentProfile profile) {

        StudentProfile saved =
                profileService.createProfile(
                        profile.getUser().getId(),
                        profile
                );

        return ResponseEntity.ok(saved);
    }
    // ✅ PUT update profile → /api/profiles/1
    @PutMapping("/{id}")
    public ResponseEntity<StudentProfile> updateProfile(@PathVariable Long id,
                                                        @RequestBody StudentProfile updatedProfile) {
        Optional<StudentProfile> existing = profileService.getProfileById(id);
        if (existing.isPresent()) {
            updatedProfile.setId(id);
            return ResponseEntity.ok(
                    profileService.updateProfile(id, updatedProfile)
            );
        }
        return ResponseEntity.notFound().build();
    }


    // ✅ DELETE profile → /api/profiles/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        if (profileService.getProfileById(id).isPresent()) {
            profileService.deleteProfile(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}