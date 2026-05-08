package com.placetrack.placetrack.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    //json ignore keeps post api response cleaner
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column
    private Double cgpa;

    @Column(length = 1000)
    private String skills;

    @Column(name = "resume_link")
    private String resumeLink;

    @Column
    private String college;

    @Column
    private String branch;

    @Column(name = "placement_status")
    private String placementStatus;

    // ─── Constructors ───────────────────────────────

    public StudentProfile() {}

    public StudentProfile(User user, Double cgpa, String skills, String resumeLink , String college,String branch ,String placementStatus ) {
        this.user = user;
        this.cgpa = cgpa;
        this.skills = skills;
        this.resumeLink = resumeLink;
        this.college = college;
        this.branch = branch;
        this.placementStatus = placementStatus;

    }

    // ─── Getters & Setters ──────────────────────────

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Double getCgpa() { return cgpa; }
    public void setCgpa(Double cgpa) { this.cgpa = cgpa; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public String getResumeLink() { return resumeLink; }
    public void setResumeLink(String resumeLink) { this.resumeLink = resumeLink; }

    public String getCollege() {return college;}
    public void setCollege(String college) {this.college = college;}

    public String getBranch() {return branch;}
    public void setBranch(String branch) {this.branch = branch;}

    public String getPlacementStatus() {return placementStatus;}
    public void setPlacementStatus(String placementStatus) {this.placementStatus = placementStatus;}
}