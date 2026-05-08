package com.placetrack.placetrack.service;

import com.placetrack.placetrack.dto.UserDashboardDTO;
import com.placetrack.placetrack.model.User;
import com.placetrack.placetrack.repository.JobApplicationRepository;
import com.placetrack.placetrack.repository.NoteRepository;
import com.placetrack.placetrack.repository.StudentProfileRepository;
import com.placetrack.placetrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private NoteRepository noteRepository;

    public UserDashboardDTO getDashboard(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        UserDashboardDTO dto = new UserDashboardDTO();

//        dto.setId(user.getId());
//        dto.setName(user.getName());
//        dto.setEmail(user.getEmail());
        dto.setUser(user);

        dto.setProfile(
                studentProfileRepository.findByUser_Id(userId).orElse(null)
        );

        dto.setApplications(
                jobApplicationRepository.findByUser_Id(userId)
        );

        dto.setNotes(
                noteRepository.findByUser_Id(userId)
        );

        return dto;
    }
}
