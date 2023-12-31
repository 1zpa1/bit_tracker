package com.zpa.bit_tracker.service;

import com.zpa.bit_tracker.dto.UserDTO;
import com.zpa.bit_tracker.entity.User;
import com.zpa.bit_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        return UserDTO.toModel(user);
    }

    public User save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public Long delete(Long id) {
        userRepository.deleteById(id);
        return id;
    }

    public UserDTO showUserInfo(Principal principal) {
        String principalName = principal.getName();
        Optional<User> userOptional = userRepository.findByUsername(principalName);
        UserDTO userDTO = new UserDTO();
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setTg(user.getTg());
            userDTO.setVideoCard(user.getVideoCard());
            userDTO.setNumberOfVideoCard(user.getNumbersOfVideoCard());
            userDTO.setHashRate(user.getHashRate());
            userDTO.setElectricityCost(user.getElectricityCost());
            userDTO.toModel(user);
        }
        return userDTO;
    }


}
