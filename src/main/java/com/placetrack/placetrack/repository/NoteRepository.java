package com.placetrack.placetrack.repository;

import com.placetrack.placetrack.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    // Get all notes of a specific user
    List<Note> findByUser_Id(Long userId);

    // Count total notes of a user
    long countByUser_Id(Long userId);
}