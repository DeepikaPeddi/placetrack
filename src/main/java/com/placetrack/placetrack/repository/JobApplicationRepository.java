package com.placetrack.placetrack.repository;

import com.placetrack.placetrack.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByUser_Id(Long userId);
    List<JobApplication> findByStatus(JobApplication.Status status);

    List<JobApplication> findByCompanyName(String companyName);
}