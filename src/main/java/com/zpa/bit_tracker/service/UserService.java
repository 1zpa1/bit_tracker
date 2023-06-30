package com.zpa.bit_tracker.service;

import com.zpa.bit_tracker.dto.UserDTO;
import com.zpa.bit_tracker.entity.User;
import com.zpa.bit_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDTO::toModel)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        return UserDTO.toModel(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Long delete(Long id) {
        userRepository.deleteById(id);
        return id;
    }
}
