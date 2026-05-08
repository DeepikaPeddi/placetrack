package com.placetrack.placetrack.dto;

import com.placetrack.placetrack.model.JobApplication;
import com.placetrack.placetrack.model.Note;
import com.placetrack.placetrack.model.StudentProfile;
import com.placetrack.placetrack.model.User;
import java.util.List;

public class UserDashboardDTO {

    private User user;
//    private Long id;
//    private String name;
//    private String email;

    private StudentProfile profile;

    private List<JobApplication> applications;

    private List<Note> notes;

    // ─── Getters & Setters ──────────────────────────

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

    public StudentProfile getProfile() {
        return profile;
    }

    public void setProfile(StudentProfile profile) {
        this.profile = profile;
    }

    public List<JobApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<JobApplication> applications) {
        this.applications = applications;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
