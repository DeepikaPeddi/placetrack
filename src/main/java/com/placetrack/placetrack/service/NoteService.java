package com.placetrack.placetrack.service;

import com.placetrack.placetrack.model.Note;
import com.placetrack.placetrack.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    // ── CREATE ───────────────────────────────────────────
    public Note saveNote(Note note) {

        note.setCreatedAt(LocalDateTime.now());

        return noteRepository.save(note);
    }

    // ── READ ALL ─────────────────────────────────────────
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // ── READ BY ID ───────────────────────────────────────
    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    // ── READ BY USER ─────────────────────────────────────
    public List<Note> getNotesByUser(Long userId) {
        return noteRepository.findByUser_Id(userId);
    }

    // ── UPDATE ───────────────────────────────────────────
    public Note updateNote(Long id, Note updatedNote) {

        return noteRepository.findById(id).map(existing -> {

            existing.setContent(updatedNote.getContent());

            return noteRepository.save(existing);

        }).orElseThrow(() ->
                new RuntimeException("Note not found with id: " + id));
    }

    // ── DELETE ───────────────────────────────────────────
    public void deleteNote(Long id) {

        if (!noteRepository.existsById(id)) {
            throw new RuntimeException("Note not found with id: " + id);
        }

        noteRepository.deleteById(id);
    }
}