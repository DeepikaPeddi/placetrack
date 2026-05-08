package com.placetrack.placetrack.repository;

import com.placetrack.placetrack.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {

    // Find profile linked to specific user
    Optional<StudentProfile> findByUser_Id(Long userId);

    // Search students by branch
    List<StudentProfile> findByBranch(String branch);

    // Search students by placement status
    List<StudentProfile> findByPlacementStatus(String placementStatus);

    // Search students by college
    List<StudentProfile> findByCollegeContainingIgnoreCase(String college);
}