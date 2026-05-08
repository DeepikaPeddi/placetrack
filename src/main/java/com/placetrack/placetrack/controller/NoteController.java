package com.placetrack.placetrack.controller;

import com.placetrack.placetrack.model.Note;
import com.placetrack.placetrack.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    // ✅ GET all notes
    // → /api/notes
    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    // ✅ GET note by ID
    // → /api/notes/1
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {

        Optional<Note> note = noteService.getNoteById(id);

        return note.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ GET all notes of a user
    // → /api/notes/user/1
    @GetMapping("/user/{userId}")
    public List<Note> getNotesByUser(@PathVariable Long userId) {

        return noteService.getNotesByUser(userId);
    }

    // ✅ POST create note
    // → /api/notes
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {

        Note saved = noteService.saveNote(note);

        return ResponseEntity.ok(saved);
    }

    // ✅ PUT update note
    // → /api/notes/1
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(
            @PathVariable Long id,
            @RequestBody Note updatedNote) {

        Optional<Note> existing = noteService.getNoteById(id);

        if (existing.isPresent()) {

            return ResponseEntity.ok(
                    noteService.updateNote(id, updatedNote)
            );
        }

        return ResponseEntity.notFound().build();
    }

    // ✅ DELETE note
    // → /api/notes/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {

        if (noteService.getNoteById(id).isPresent()) {

            noteService.deleteNote(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
