package com.placetrack.placetrack.service;

import com.placetrack.placetrack.model.User;
import com.placetrack.placetrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service  // Marks this as a Spring-managed service bean
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // ── CREATE ──────────────────────────────────────────
    public User createUser(User user) {
        // You can add validation here before saving
        return userRepository.save(user);
    }

    // ── READ ALL ─────────────────────────────────────────
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ── READ BY ID ───────────────────────────────────────
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // ── READ BY EMAIL ────────────────────────────────────
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email); // must exist in repo
    }

    // ── UPDATE ───────────────────────────────────────────
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            //existingUser.setRole(updatedUser.getRole());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // ── DELETE ───────────────────────────────────────────
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}