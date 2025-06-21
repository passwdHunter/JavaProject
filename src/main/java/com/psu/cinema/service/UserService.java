package com.psu.cinema.service;

import com.psu.cinema.entity.User;
import com.psu.cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> createUser(User user) {
        return Optional.of(userRepository.save(user));
    }

    public Optional<User> updateUser(Long id, User updatedUser) {
        if (!userRepository.existsById(id)) {
            return Optional.empty();
        }
        updatedUser.setId(id);
        return Optional.of(userRepository.save(updatedUser));
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}